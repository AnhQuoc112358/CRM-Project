package helloservlet.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helloservlet.entity.UserEntity;
import helloservlet.repository.UserRepository;

@WebFilter(filterName = "authenFilter", urlPatterns = { "/*" })
public class AuthenticationFilter implements Filter {
	private UserRepository userRepository = new UserRepository();
	private static final List<String> adminPath = Arrays.asList("/user-modify", "/user-delete", "/user-add",
			"/role-modify", "/role-delete", "/role-add", "/project-modify", "/project-delete", "/project-add",
			"/task-modify", "/task-delete", "/task-add");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String path = req.getServletPath();
		if (path.equals("/login") || path.equals("/login.jsp") || path.equals("/logout")) {
			chain.doFilter(request, response);
		} else if (adminPath.contains(path)) {
			doFilterAdmin(req, resp, chain);
		} else {
			doFilterUser(req, resp, chain);
		}
	}

	public void doFilterUser(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter user activated");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		UserEntity userNow = (UserEntity) session.getAttribute("userNow");
		System.out.println("usernow user filter 1: " + userNow);
		if (userNow != null) { // userNow was created from login page
			chain.doFilter(request, response);
		} else { // user access page from other path
			String emailCheck = "";
			String passwordCheck = "";
			String contextPath = req.getContextPath();
			try {
				Cookie[] cookies = req.getCookies();
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("emailRemember")) {
						emailCheck = cookie.getValue();
					}
					if (cookie.getName().equals("passwordRemember")) {
						passwordCheck = cookie.getValue();
					}
				}
			} catch (Exception e) {
				System.out.println("Error get all cookie" + e.getMessage());
			}
			if (emailCheck.trim().length() > 0 && passwordCheck.trim().length() > 0) { // Account saved
				System.out.println("Filter user accept");
				userNow = userRepository.findUserByEmailAndPassword(emailCheck, passwordCheck);
				session.setAttribute("userNow", userNow);
				System.out.println("usernow user filter 2: " + userNow);
				chain.doFilter(request, response);
			} else {
				System.out.println("Filter user deny");
				resp.sendRedirect(contextPath + "/login");
			}
		}
	}

	public void doFilterAdmin(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter admin activated");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		UserEntity userNow = (UserEntity) session.getAttribute("userNow");
		System.out.println("usernow admin filter 1: " + userNow);
		if (userNow != null) { // userNow was created from login page
			if (userNow.getRole().getId() == 1) { // The user is just a member
				resp.sendRedirect("403.html");
			} else { // The user is an admin or manager
				chain.doFilter(request, response);
			}
		} else { // user access page from other path
			String emailCheck = "";
			String passwordCheck = "";
			String contextPath = req.getContextPath();
			try {
				Cookie[] cookies = req.getCookies();
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("emailRemember")) {
						emailCheck = cookie.getValue();
					}
					if (cookie.getName().equals("passwordRemember")) {
						passwordCheck = cookie.getValue();
					}
				}
			} catch (Exception e) {
				System.out.println("Error get all cookie" + e.getMessage());
			}
			if (emailCheck.trim().length() > 0 && passwordCheck.trim().length() > 0) { // Account saved
				userNow = userRepository.findUserByEmailAndPassword(emailCheck, passwordCheck);
				session.setAttribute("userNow", userNow);
				System.out.println("usernow admin filter 2: " + userNow);
				if (userNow.getRole().getId() == 1) { // The user is just a member
					resp.sendRedirect("403.html");
				} else { // The user is an admin or manager
					System.out.println("Filter admin accept");
					chain.doFilter(request, response);
				}
			} else { // Account did not save and the user did not login
				System.out.println("Filter admin deny");
				resp.sendRedirect(contextPath + "/login");
			}
		}
	}
}
