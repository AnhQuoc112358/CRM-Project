package helloservlet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import helloservlet.service.RoleService;
@WebServlet(name = "roleServlet", urlPatterns = { "/role-add", "/role-table", "/role-modify", "/role-delete" })
public class RoleController extends HttpServlet {
	private RoleService roleService = new RoleService();
	private int idModify;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/role-add")) {
			doGetRoleAdd(req, resp);
		} else if (path.equals("/role-table")) {
			doGetRoleTable(req, resp);
		} else if (path.equals("/role-modify")) {
			doGetRoleModify(req, resp);
		} else if (path.equals("/role-delete")) {
			doGetRoleDelete(req, resp);
		}
	}

	private void doGetRoleTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listRole", roleService.getAllRole());
		req.getRequestDispatcher("role-table.jsp").forward(req, resp);
	}

	private void doGetRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}

	private void doGetRoleModify(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		idModify = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("roleModify", roleService.findRoleById(idModify));
		req.getRequestDispatcher("role-modify.jsp").forward(req, resp);
	}

	private void doGetRoleDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		roleService.deleteRoleById(id);
		resp.sendRedirect(req.getContextPath() + "/role-table");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/role-add")) {
			doPostRoleAdd(req, resp);
		} else if (path.equals("/role-modify")) {
			doPostRoleModify(req, resp);
		}
	}

	private void doPostRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		roleService.insertRole(name, description);
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}

	private void doPostRoleModify(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");  
		roleService.modifyRoleById(idModify, name, description);
		resp.sendRedirect(req.getContextPath() + "/role-table");
	}
}
