package helloservlet.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import helloservlet.config.MysqlConfig;
import helloservlet.entity.ProjectEntity;
import java.time.LocalDate;

public class ProjectRepository {
	public List<ProjectEntity> findAllProject() {
		String query = "SELECT * FROM projects;";
		Connection connection = MysqlConfig.getConnection();
		List<ProjectEntity> listProject = new ArrayList<ProjectEntity>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProjectEntity entity = new ProjectEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				entity.setStart_date(resultSet.getDate("start_date").toLocalDate());
				if (resultSet.getDate("end_date") != null) {
					entity.setEnd_date(resultSet.getDate("end_date").toLocalDate());
				}
				listProject.add(entity);
			}
		} catch (Exception e) {
			System.out.println("Error findAllProject " + e.getLocalizedMessage());
		}
		return listProject;
	}

	public ProjectEntity findProjectById(int id) {
		ProjectEntity entity = new ProjectEntity();
		String query = "SELECT * FROM projects WHERE id = ?;";
		try (Connection connection = MysqlConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					entity.setId(resultSet.getInt("id"));
					entity.setName(resultSet.getString("name"));
					entity.setStart_date(resultSet.getDate("start_date").toLocalDate());
					if (resultSet.getDate("end_date") != null) {
						entity.setEnd_date(resultSet.getDate("end_date").toLocalDate());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error findProjectById " + e.getLocalizedMessage());
		}
		return entity;
	}

	public int insertProject(String name, LocalDate start_date, LocalDate end_date) {
		int count = 0;
		try {
			String query = "INSERT INTO projects (name, start_date, end_date) VALUES (?, ?, ?);";
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setDate(2, Date.valueOf(start_date)); // Chuyển đổi LocalDate thành java.sql.Date
			if (end_date != null) {
				preparedStatement.setDate(3, Date.valueOf(end_date)); // Chuyển đổi LocalDate thành java.sql.Date
			} else {
				preparedStatement.setNull(3, Types.DATE); // Đặt giá trị null cho trường end_date
			}
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error insertproject " + e.getLocalizedMessage());
		}
		return count;
	}

	public int modifyProjectById(int idProjectModify, String name, LocalDate start_date, LocalDate end_date) {
		int count = 0;
		String query = "UPDATE projects p SET p.name = ?, p.start_date = ?, p.end_date = ?  WHERE p.id = ?;";
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setDate(2, Date.valueOf(start_date)); // Chuyển đổi LocalDate thành java.sql.Date
			if (end_date != null) {
				preparedStatement.setDate(3, Date.valueOf(end_date)); // Chuyển đổi LocalDate thành java.sql.Date
			} else {
				preparedStatement.setNull(3, Types.DATE); // Đặt giá trị null cho trường end_date
			}
			preparedStatement.setInt(4, idProjectModify);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error modifyProjectById " + e.getLocalizedMessage());
		}
		return count;
	}

	public int deleteProjectById(int id) {
		int count = 0;
		String query = "DELETE FROM projects p WHERE p.id = ?";
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error deleteProjectById " + e.getLocalizedMessage());
		}
		return count;
	}
}
