package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import helloservlet.config.MysqlConfig;
import helloservlet.entity.UserEntity;
import helloservlet.service.RoleService;

public class UserRepository {
	private RoleService roleService = new RoleService();
//	public List<UserEntity> findByEmailAndPassword(String email, String password) {
//		// Bước 2 : Chuẩn bị câu query ( truy vấn )
//		String query = "SELECT * FROM users u WHERE u.email = ? AND u.password = ?";
//
//		// Bước 3 : Mở kết nối csdl
//		Connection connection = MysqlConfig.getConnection();
//		// Tạo list UserEntity để lưu trữ từng dòng dữ liệu query được
//		List<UserEntity> listUser = new ArrayList<UserEntity>();
//		try {
//			// Bước 4 : Truyền câu query vào CSDL vừa mở kết nối thông qua PrepareStatement
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			// Gán giá trị tham tham số dấu chấm ? bên trong câu query
//			preparedStatement.setString(1, email);
//			preparedStatement.setString(2, password);
//
//			// Bước 5 : Thông báo cho CSDL biết và thực thi câu query
//			// Có cách thực thi
//			// executeQuery : Giành cho câu truy vấn là câu SELECT => luôn trả ra ResultSet
//			// executeUpdate : Tất cả câu truy cấn còn lại ngoài câu SELECT ví dụ :
//			// INSERT,UPDATE, DELETE
//			ResultSet resultSet = preparedStatement.executeQuery();
//
//			// Bước 6 : Duyệt từng dòng dữ liệu query được và gán vào trong List<UserEntity>
//			while (resultSet.next()) {
//				UserEntity entity = new UserEntity();
//				entity.setId(resultSet.getInt("id")); // resultSet.getInt(tên cột database) Lấy giá trị của cột id gán
//				// vào thuộc tính id của userentity
//				entity.setEmail(resultSet.getString("email"));
//				listUser.add(entity);
//			}
//		} catch (Exception e) {
//			System.out.println("Error findByEmailAndPassword " + e.getLocalizedMessage());
//		}
//		return listUser;
//	}

	public UserEntity findUserByEmailAndPassword(String email, String password) {
		UserEntity entity = null;
		String query = "SELECT * FROM users u WHERE u.email = ? AND u.password = ?;";
		try (Connection connection = MysqlConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					entity = new UserEntity();
					entity.setId(resultSet.getInt("id"));
					entity.setEmail(resultSet.getString("email"));
					entity.setPassword(resultSet.getString("password"));
					entity.setUsername(resultSet.getString("username"));
					entity.setFirstname(resultSet.getString("firstname"));
					entity.setLastname(resultSet.getString("lastname"));
					entity.setPhone_number(resultSet.getString("phone_number"));
					entity.setCountry(resultSet.getString("country"));
					entity.setAvatar(resultSet.getString("avatar"));
					entity.setRole(roleService.findRoleById(resultSet.getInt("role_id")));
				}
			}
		} catch (Exception e) {
			System.out.println("Error findUserByEmailAndPassword " + e.getLocalizedMessage());
		}
		return entity;
	}
	
	public List<UserEntity> findAllUser() {
		String query = "SELECT * FROM users u JOIN roles r ON u.role_id = r.id ;";
		Connection connection = MysqlConfig.getConnection();
		List<UserEntity> listUser = new ArrayList<UserEntity>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserEntity entity = new UserEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setEmail(resultSet.getString("email"));
				entity.setPassword(resultSet.getString("password"));
				entity.setUsername(resultSet.getString("username"));
				entity.setFirstname(resultSet.getString("firstname"));
				entity.setLastname(resultSet.getString("lastname"));
				entity.setPhone_number(resultSet.getString("phone_number"));
				entity.setCountry(resultSet.getString("country"));
				entity.setAvatar(resultSet.getString("avatar"));
				entity.setRole(roleService.findRoleById(resultSet.getInt("role_id")));
				listUser.add(entity);
			}
		} catch (Exception e) {
			System.out.println("Error findAllUser " + e.getLocalizedMessage());
		}
		return listUser;
	}

	public UserEntity findUserById(int id) {
		UserEntity entity = null;
		String query = "SELECT * FROM users u JOIN roles r ON u.role_id = r.id WHERE u.id = ?;";
		try (Connection connection = MysqlConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					entity = new UserEntity();
					entity.setId(resultSet.getInt("id"));
					entity.setEmail(resultSet.getString("email"));
					entity.setPassword(resultSet.getString("password"));
					entity.setUsername(resultSet.getString("username"));
					entity.setFirstname(resultSet.getString("firstname"));
					entity.setLastname(resultSet.getString("lastname"));
					entity.setPhone_number(resultSet.getString("phone_number"));
					entity.setCountry(resultSet.getString("country"));
					entity.setAvatar(resultSet.getString("avatar"));
					entity.setRole(roleService.findRoleById(resultSet.getInt("role_id")));
				}
			}
		} catch (Exception e) {
			System.out.println("Error findUserById " + e.getLocalizedMessage());
		}
		return entity;
	}

	public int insertUser(String email, String password, String username, String firstname, String lastname,
			String phone_number, String country, int role_id) {
		int count = 0;
		try {
			String query = "INSERT INTO users (email, password,username,firstname,lastname,phone_number,country,role_id)"
					+ "VALUES(?,?,?,?,?,?,?,?)";
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, username);
			preparedStatement.setString(4, firstname);
			preparedStatement.setString(5, lastname);
			preparedStatement.setString(6, phone_number);
			preparedStatement.setString(7, country);
			preparedStatement.setInt(8, role_id);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error insert user " + e.getLocalizedMessage());
		}
		return count;
	}

	public int modifyUserById(int idUserModify, String email, String password, String username, String firstname,
			String lastname, String phone_number, String country, int role_id) {
		int count = 0;
		String query = "UPDATE users u SET u.email = ?, u.password = ?,u.username = ?,u.firstname = ?,u.lastname = ?,"
				+ "u.phone_number = ?,u.country = ?,u.role_id = ? WHERE u.id  = ?";
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, username);
			preparedStatement.setString(4, firstname);
			preparedStatement.setString(5, lastname);
			preparedStatement.setString(6, phone_number);
			preparedStatement.setString(7, country);
			preparedStatement.setInt(8, role_id);
			preparedStatement.setInt(9, idUserModify);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error modifyUserById " + e.getLocalizedMessage());
		}
		return count;
	}

	public int deleteUserById(int id) {
		int count = 0;
		String query = "DELETE FROM users u WHERE u.id = ?";
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error deleteUserById " + e.getLocalizedMessage());
		}
		return count;
	}
}
