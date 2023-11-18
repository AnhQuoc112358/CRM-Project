package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.RoleEntity;

public class RoleRepository {

	public List<RoleEntity> findAllRole() {
		List<RoleEntity> listRole = new ArrayList<RoleEntity>();
		String query = "SELECT * FROM roles";
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				RoleEntity roleEntity = new RoleEntity();
				roleEntity.setId(resultSet.getInt("id"));
				roleEntity.setName(resultSet.getString("name"));
				roleEntity.setDescription(resultSet.getString("description"));
				listRole.add(roleEntity);
			}
		} catch (Exception e) {
			System.out.println("Error findAllRole " + e.getLocalizedMessage());
		}
		return listRole;
	}
	public RoleEntity findRoleById(int id) {
		RoleEntity entity = new RoleEntity();
		try {
			String query = "SELECT * FROM roles WHERE id = ?;";
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				entity.setDescription(resultSet.getString("description"));
			}
		} catch (Exception e) {
			System.out.println("Error findRoleById " + e.getLocalizedMessage());
		}
		return entity;
	}
	
	public int insertRole(String name, String description) {
		int count = 0;
		try {
			String query = "INSERT INTO roles(name,description) VALUES(?,?)";
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, description);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error insert role " + e.getLocalizedMessage());
		}
		return count;
	}

	public int modifyRoleById(int id, String name, String description) {
		int count = 0;
		String query = "UPDATE roles r SET r.name = ?, r.description = ? WHERE r.id  = ?";
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, description);
			preparedStatement.setInt(3, id);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error modifyRoleById " + e.getLocalizedMessage());
		}
		return count;
	}

	public int deleteRoleById(int id) {
		int count = 0;
		String query = "DELETE FROM roles r WHERE r.id = ?";
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error deleteRoleById " + e.getLocalizedMessage());
		}
		return count;
	}
}
