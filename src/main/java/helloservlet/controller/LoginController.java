package helloservlet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.service.LoginService;

@WebServlet(name = "loginController", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	LoginService loginService = new LoginService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Cookie[] listCookie = req.getCookies();
			for (Cookie cookie : listCookie) {
				if (cookie.getName().equals("emailRemember")) {
					String emailRemember = cookie.getValue();
					req.setAttribute("emailRemember", emailRemember);
				}
				if (cookie.getName().equals("passwordRemember")) {
					String passwordRemember = cookie.getValue();
					req.setAttribute("passwordRemember", passwordRemember);
				}
			}
		} catch (Exception e) {
			System.out.println("Error Get email and password from Cookie " + e.getMessage());
		}
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		String contextPath = req.getContextPath();
		boolean checkLogin = loginService.checkLogin(email, password, remember, resp, req);
		System.out.println("Check login " + checkLogin);
		if (checkLogin) {
			resp.sendRedirect(contextPath + "/home");
		} else {
			resp.sendRedirect(contextPath + "/login");
		}
	}
}
