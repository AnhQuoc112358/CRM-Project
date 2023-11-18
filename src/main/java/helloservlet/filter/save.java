//package helloservlet.filter;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import helloservlet.config.MysqlConfig;
//import helloservlet.entity.UserEntity;
//
//@WebFilter(filterName = "authenFilter", urlPatterns = { "/*" })
//public class save implements Filter {
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		System.out.println("Đã kích hoạt Filter");
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse resp = (HttpServletResponse) response;
//		String email = "";
//		String password = "";
//		String contextPath = req.getContextPath();
//		try {
//			Cookie[] cookies = req.getCookies();
//			for (Cookie cookie : cookies) {
//				if (cookie.getName().equals("email")) {
//					email = cookie.getValue();
//				}
//				if (cookie.getName().equals("password")) {
//					password = cookie.getValue();
//				}
//			}
//		} catch (Exception e) {
//			System.out.println("Error get all cookie" + e.getMessage());
//		}
//		if (email.trim().length() > 0 && password.trim().length() > 0) {
////					Cho phép đi vào link
//
//			// Bước 2 : Chuẩn bị câu query ( truy vấn )
//			String query = "SELECT *\n" + "FROM users u\n" + "where u.email = ? AND u.password = ?";
//
//			// Bước 3 : Mở kết nối csdl
//			Connection connection = MysqlConfig.getConnection();
//
//			try {
//				// Bước 4 : Truyền câu query vào CSDL vừa mở kết nối thông qua PrepareStatement
//				PreparedStatement preparedStatement = connection.prepareStatement(query);
//				// Gán giá trị tham tham số dấu chấm ? bên trong câu query
//				preparedStatement.setString(1, email);
//				preparedStatement.setString(2, password);
//
//				// Bước 5 : Thông báo cho CSDL biết và thực thi câu query
//				// Có cách thực thi
//				// executeQuery : Giành cho câu truy vấn là câu SELECT => luôn trả ra ResultSet
//				// executeUpdate : Tất cả câu truy cấn còn lại ngoài câu SELECT ví dụ : INSERT,
//				// UPDATE, DELETE
//				ResultSet resultSet = preparedStatement.executeQuery();
//				// Tạo list UserEntity để lưu trữ từng dòng dữ liệu query được
//				List<UserEntity> listUser = new ArrayList<UserEntity>();
//
//				// Bước 6 : Duyệt từng dòng dữ liệu query được và gán vào trong List<UserEntity>
//				while (resultSet.next()) {
//					UserEntity entity = new UserEntity();
//					entity.setId(resultSet.getInt("id")); // resultSet.getInt(tên cột database) Lấy giá trị của cột id
//															// gán
//					// vào thuộc tính id của userentity
//					entity.setFullname(resultSet.getString("fullname"));
//					listUser.add(entity);
//				}
//
//				// kiểm tra đăng nhập bằng cách kiểm tra View listuser có giá trị hay không
//				if (listUser.size() > 0) {
//					System.out.println("Đăng nhập thành công role add");
//					chain.doFilter(request, response);
//
//				} else {
//					System.out.println("Đăng nhập thất bại");
//					resp.sendRedirect(contextPath + "/login");
//				}
//
//			}
//			catch (SQLException e) {
//				e.printStackTrace();
//				resp.sendRedirect(contextPath + "/login");
//			}
//
////			chain.doFilter(request, response);
//		}
//		else 
//		{
//			System.out.println("quay lại login");
//			resp.sendRedirect(contextPath + "/login");
//
//		}
//
//	}
//
//}
