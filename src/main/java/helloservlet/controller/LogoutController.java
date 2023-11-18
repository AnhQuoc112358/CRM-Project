package helloservlet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import helloservlet.service.LogoutService;
@WebServlet(name = "logout", urlPatterns = { "/logout"})
public class LogoutController extends HttpServlet{
	LogoutService logoutService = new LogoutService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logoutService.logout(resp, req);
		resp.sendRedirect("login.jsp");
		System.out.println("Logout");
	}
}
