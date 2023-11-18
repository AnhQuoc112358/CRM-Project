package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import helloservlet.config.MysqlConfig;
import helloservlet.entity.StatusEntity;

public class StatusRepository {
	public List<StatusEntity> findAllStatus() {
		String query = "SELECT * FROM status;";
		Connection connection = MysqlConfig.getConnection();
		List<StatusEntity> listStatus = new ArrayList<StatusEntity>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				StatusEntity entity = new StatusEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				listStatus.add(entity);
			}
		} catch (Exception e) {
			System.out.println("Error findAllStatus " + e.getLocalizedMessage());
		}
		return listStatus;
	}

	public StatusEntity findStatusById(int id) {
		StatusEntity entity = new StatusEntity();
		String query = "SELECT * FROM status WHERE id = ?;";
		try (Connection connection = MysqlConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					entity.setId(resultSet.getInt("id"));
					entity.setName(resultSet.getString("name"));
				}
			}
		} catch (Exception e) {
			System.out.println("Error findStatusById " + e.getLocalizedMessage());
		}
		return entity;
	}

}
