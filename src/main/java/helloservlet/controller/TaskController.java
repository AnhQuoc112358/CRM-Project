package helloservlet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import helloservlet.service.ProjectService;
import helloservlet.service.TaskService;
import helloservlet.service.UserService;

@WebServlet(name = "taskServlet", urlPatterns = { "/task-table", "/task-add", "/task-modify", "/task-delete"})
public class TaskController extends HttpServlet {
	private TaskService taskService = new TaskService();
	private UserService userService = new UserService();
	private ProjectService projectService = new ProjectService();
	private int idModify;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/task-table")) {
			doGetTaskTable(req, resp);
		} else if (path.equals("/task-add")) {
			doGetTaskAdd(req, resp);
		} else if (path.equals("/task-modify")) {
			doGetTaskModify(req, resp);
		} else if (path.equals("/task-delete")) {
			doGetTaskDelete(req, resp);
		}
	}

	private void doGetTaskTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listTask", taskService.getAllTask());
		req.getRequestDispatcher("task-table.jsp").forward(req, resp);
	}

	private void doGetTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listUser", userService.getAllUser());
		req.setAttribute("listProject", projectService.getAllProject());
		req.getRequestDispatcher("task-add.jsp").forward(req, resp);
	}

	private void doGetTaskModify(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		idModify = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("taskModify", taskService.findTaskById(idModify));
		req.setAttribute("listUser", userService.getAllUser());
		req.setAttribute("listProject", projectService.getAllProject());
		req.getRequestDispatcher("task-modify.jsp").forward(req, resp);
	}

	private void doGetTaskDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		taskService.deleteTaskById(id);
		resp.sendRedirect(req.getContextPath() + "/task-table");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/task-add")) {
			doPostTaskAdd(req, resp);
		} else if (path.equals("/task-modify")) {
			doPostTaskModify(req, resp);
		}
	}

	private void doPostTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String startDateStr = req.getParameter("start_date");
		String endDateStr = req.getParameter("end_date");
		int user_id = Integer.parseInt(req.getParameter("user_id"));
		int project_id = Integer.parseInt(req.getParameter("project_id"));
		taskService.insertTask(name, startDateStr, endDateStr, user_id, project_id);
		resp.sendRedirect(req.getContextPath() + "/task-add");
	}

	private void doPostTaskModify(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String startDateStr = req.getParameter("start_date");
		String endDateStr = req.getParameter("end_date");
		int user_id = Integer.parseInt(req.getParameter("user_id"));
		int project_id = Integer.parseInt(req.getParameter("project_id"));
		taskService.modifyTaskById(idModify, name, startDateStr, endDateStr, user_id, project_id);
		resp.sendRedirect(req.getContextPath() + "/task-table");
	}
}
