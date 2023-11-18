//package helloservlet.api;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.gson.Gson;
//
//import helloservlet.payload.BasicResponse;
//import helloservlet.service.RoleService;
//@WebServlet(name = "apiRoleController", urlPatterns = {"/api/role"})
//public class ApiRoleController extends HttpServlet{
//	private RoleService roleService = new RoleService();
//	private Gson gson = new Gson();
//	
//	/*
//	 * "status
//	 */
//	@Override
//	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		int id = Integer.parseInt(req.getParameter("id"));
//		boolean isSuccess = roleService.deleteRole(id);
//		BasicResponse basicResponse = new BasicResponse();
//		basicResponse.setStatusCode(200);
//		basicResponse.setMessage("");
//		basicResponse.setData(isSuccess);
//		String dataJson = gson.toJson(basicResponse);
//		PrintWriter out = resp.getWriter();
//		resp.setContentType("application/json");
//		resp.setCharacterEncoding("UTF8");
//		out.print(dataJson);
//		out.flush(); // xả kết nối
//		System.out.println("Kiem tra delete " + id);
//	}
//}
