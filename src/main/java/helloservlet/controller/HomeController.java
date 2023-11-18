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

@WebServlet(name = "homeServlet", urlPatterns = { "", "/home"})
public class HomeController extends HttpServlet {
	private TaskService taskService = new TaskService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TaskEntity> listTaskNotStarted = taskService.findTaskByStatusId(1);
		List<TaskEntity> listTaskInProgress = taskService.findTaskByStatusId(2);
		List<TaskEntity> listTaskCompleted = taskService.findTaskByStatusId(3);
		req.setAttribute("percentTask",
				taskService.percentTask(listTaskNotStarted, listTaskInProgress, listTaskCompleted));
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
