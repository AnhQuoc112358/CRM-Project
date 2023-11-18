package helloservlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.entity.TaskEntity;
import helloservlet.service.TaskService;
import helloservlet.service.ProjectService;

@WebServlet(name = "projectServlet", urlPatterns = { "/project-table", "/project-add", "/project-modify",
		"/project-delete", "/project-details"})
public class ProjectController extends HttpServlet {
	private ProjectService projectService = new ProjectService();
	private int idModify;
	private TaskService taskService = new TaskService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/project-table")) {
			doGetProjectTable(req, resp);
		} else if (path.equals("/project-add")) {
			doGetProjectAdd(req, resp);
		} else if (path.equals("/project-modify")) {
			doGetProjectModify(req, resp);
		} else if (path.equals("/project-delete")) {
			doGetProjectDelete(req, resp);
		} else if (path.equals("/project-details")) {
			doGetProjectDetails(req, resp);
		}
	}

	private void doGetProjectTable(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("listProject", projectService.getAllProject());
		req.getRequestDispatcher("project-table.jsp").forward(req, resp);
	}

	private void doGetProjectAdd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("project-add.jsp").forward(req, resp);
	}

	private void doGetProjectModify(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		idModify = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("projectModify", projectService.findProjectById(idModify));
		req.getRequestDispatcher("project-modify.jsp").forward(req, resp);
	}

	private void doGetProjectDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		projectService.deleteProjectById(id);
		resp.sendRedirect(req.getContextPath() + "/project-table");
	}
	
	private void doGetProjectDetails(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("projectDetails", projectService.findProjectById(id));
		List<TaskEntity> listTaskNotStarted = taskService.findTaskByProjectIdAndStatusId(id, 1);
		req.setAttribute("listTaskNotStarted", listTaskNotStarted);
		List<TaskEntity> listTaskInProgress = taskService.findTaskByProjectIdAndStatusId(id, 2);
		req.setAttribute("listTaskInProgress", listTaskInProgress);
		List<TaskEntity> listTaskCompleted = taskService.findTaskByProjectIdAndStatusId(id, 3);
		req.setAttribute("listTaskCompleted", listTaskCompleted);
		req.setAttribute("percentTask",
				taskService.percentTask(listTaskNotStarted, listTaskInProgress, listTaskCompleted));
		req.getRequestDispatcher("project-details.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/project-add")) {
			doPostProjectAdd(req, resp);
		} else if (path.equals("/project-modify")) {
			doPostProjectModify(req, resp);
		}
	}

	private void doPostProjectAdd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String startDateStr = req.getParameter("start_date");
		String endDateStr = req.getParameter("end_date");
		projectService.insertProject(name, startDateStr, endDateStr);
		req.getRequestDispatcher("project-add.jsp").forward(req, resp);
	}

	private void doPostProjectModify(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String startDateStr = req.getParameter("start_date");
		String endDateStr = req.getParameter("end_date");
		projectService.modifyProjectById(idModify, name, startDateStr, endDateStr);
		resp.sendRedirect(req.getContextPath() + "/project-table");
	}
}
