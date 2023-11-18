package helloservlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helloservlet.service.UserService;
import helloservlet.entity.TaskEntity;
import helloservlet.entity.UserEntity;
import helloservlet.service.RoleService;
import helloservlet.service.TaskService;

@WebServlet(name = "userServlet", urlPatterns = { "/user-table", "/user-add", "/user-modify", "/user-delete",
		"/user-details", "/information-modify" })
public class UserController extends HttpServlet {
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	private TaskService taskService = new TaskService();
	private int idModify;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/user-table")) {
			doGetUserTable(req, resp);
		} else if (path.equals("/user-add")) {
			doGetUserAdd(req, resp);
		} else if (path.equals("/user-modify")) {
			doGetUserModify(req, resp);
		} else if (path.equals("/user-delete")) {
			doGetUserDelete(req, resp);
		} else if (path.equals("/user-details")) {
			doGetUserDetails(req, resp);
		}
	}

	private void doGetUserTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listUser", userService.getAllUser());
		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
	}

	private void doGetUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listRole", roleService.getAllRole());
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}

	private void doGetUserModify(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		idModify = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("userModify", userService.findUserById(idModify));
		req.setAttribute("listRole", roleService.getAllRole());
		req.getRequestDispatcher("user-modify.jsp").forward(req, resp);
	}

	private void doGetUserDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		userService.deleteUserById(id);
		resp.sendRedirect(req.getContextPath() + "/user-table");
	}

	private void doGetUserDetails(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("userDetails", userService.findUserById(id));
		List<TaskEntity> listTaskNotStarted = taskService.findTaskByUserIdAndStatusId(id, 1);
		List<TaskEntity> listTaskInProgress = taskService.findTaskByUserIdAndStatusId(id, 2);
		List<TaskEntity> listTaskCompleted = taskService.findTaskByUserIdAndStatusId(id, 3);
		req.setAttribute("listTaskByUser", taskService.findTaskByUserId(id));
		req.setAttribute("percentTask",
				taskService.percentTask(listTaskNotStarted, listTaskInProgress, listTaskCompleted));
		req.getRequestDispatcher("user-details.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/user-add")) {
			doPostUserAdd(req, resp);
		} else if (path.equals("/user-modify")) {
			doPostUserModify(req, resp);
		} else if (path.equals("/information-modify")) {
			doPostInformationModify(req, resp);
		}
	}

	private void doPostUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String username = req.getParameter("username");
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String phone_number = req.getParameter("phone_number");
		String country = req.getParameter("country");
		int role_id = Integer.parseInt(req.getParameter("role_id"));
		userService.insertUser(email, password, username, firstname, lastname, phone_number, country, role_id);
		resp.sendRedirect(req.getContextPath() + "/user-add");
	}

	private void doPostUserModify(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String username = req.getParameter("username");
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String phone_number = req.getParameter("phone_number");
		String country = req.getParameter("country");
		int role_id = Integer.parseInt(req.getParameter("role_id"));
		userService.modifyUserById(idModify, email, password, username, firstname, lastname, phone_number, country,
				role_id);
		resp.sendRedirect(req.getContextPath() + "/user-table");
	}
	
	private void doPostInformationModify(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserEntity userNow = (UserEntity) session.getAttribute("userNow");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String username = req.getParameter("username");
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String phone_number = req.getParameter("phone_number");
		String country = req.getParameter("country");
		int id = userNow.getId();
		int role_id = userNow.getRole().getId();
		userService.modifyUserById(id, email, password, username, firstname, lastname, phone_number, country,
				role_id);
		session.setAttribute("userNow", userService.findUserById(id));
		resp.sendRedirect("information.jsp");
	}
}
