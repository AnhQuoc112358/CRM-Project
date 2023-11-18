package helloservlet.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import helloservlet.config.MysqlConfig;
import helloservlet.entity.TaskEntity;

import java.time.LocalDate;

public class TaskRepository {
	private UserRepository userRepository = new UserRepository();
	private ProjectRepository projectRepository = new ProjectRepository();
	private StatusRepository statusRepository = new StatusRepository();

	public List<TaskEntity> findAllTask() {
		String query = "SELECT * FROM tasks;";
		Connection connection = MysqlConfig.getConnection();
		List<TaskEntity> listTask = new ArrayList<TaskEntity>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				TaskEntity entity = new TaskEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				if (resultSet.getDate("start_date") != null) {
					entity.setStart_date(resultSet.getDate("start_date").toLocalDate());
				}
				if (resultSet.getDate("end_date") != null) {
					entity.setEnd_date(resultSet.getDate("end_date").toLocalDate());
				}
				entity.setUser(userRepository.findUserById(resultSet.getInt("user_id")));
				entity.setProject(projectRepository.findProjectById(resultSet.getInt("project_id")));
				entity.setStatus(statusRepository.findStatusById(resultSet.getInt("status_id")));
				listTask.add(entity);
			}
		} catch (Exception e) {
			System.out.println("Error findAllTask " + e.getLocalizedMessage());
		}
		return listTask;
	}

	public TaskEntity findTaskById(int id) {
		TaskEntity entity = new TaskEntity();
		try {
			String query = "SELECT * FROM tasks WHERE id = ?;";
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				if (resultSet.getDate("start_date") != null) {
					entity.setStart_date(resultSet.getDate("start_date").toLocalDate());
				}
				if (resultSet.getDate("end_date") != null) {
					entity.setEnd_date(resultSet.getDate("end_date").toLocalDate());
				}
				entity.setUser(userRepository.findUserById(resultSet.getInt("user_id")));
				entity.setProject(projectRepository.findProjectById(resultSet.getInt("project_id")));
				entity.setStatus(statusRepository.findStatusById(resultSet.getInt("status_id")));
			}
		} catch (Exception e) {
			System.out.println("Error findTaskById " + e.getLocalizedMessage());
		}
		return entity;
	}

	public List<TaskEntity> findTaskByUserIdAndStatusId(int user_id, int status_id) {
		String query = "SELECT * FROM tasks t WHERE user_id = ? AND status_id = ?; ";
		Connection connection = MysqlConfig.getConnection();
		List<TaskEntity> listTask = new ArrayList<TaskEntity>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, status_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				TaskEntity entity = new TaskEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				if (resultSet.getDate("start_date") != null) {
					entity.setStart_date(resultSet.getDate("start_date").toLocalDate());
				}
				if (resultSet.getDate("end_date") != null) {
					entity.setEnd_date(resultSet.getDate("end_date").toLocalDate());
				}
				entity.setUser(userRepository.findUserById(resultSet.getInt("user_id")));
				entity.setProject(projectRepository.findProjectById(resultSet.getInt("project_id")));
				entity.setStatus(statusRepository.findStatusById(resultSet.getInt("status_id")));
				listTask.add(entity);
			}
		} catch (Exception e) {
			System.out.println("Error findTaskByUserIdAndStatusId " + e.getLocalizedMessage());
		}
		return listTask;
	}

	public List<TaskEntity> findTaskByUserId(int user_id) {
		String query = "SELECT * FROM tasks t WHERE user_id = ?; ";
		Connection connection = MysqlConfig.getConnection();
		List<TaskEntity> listTask = new ArrayList<TaskEntity>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, user_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				TaskEntity entity = new TaskEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				if (resultSet.getDate("start_date") != null) {
					entity.setStart_date(resultSet.getDate("start_date").toLocalDate());
				}
				if (resultSet.getDate("end_date") != null) {
					entity.setEnd_date(resultSet.getDate("end_date").toLocalDate());
				}
				entity.setUser(userRepository.findUserById(resultSet.getInt("user_id")));
				entity.setProject(projectRepository.findProjectById(resultSet.getInt("project_id")));
				entity.setStatus(statusRepository.findStatusById(resultSet.getInt("status_id")));
				listTask.add(entity);
			}
		} catch (Exception e) {
			System.out.println("Error findTaskByUserId " + e.getLocalizedMessage());
		}
		return listTask;
	}

	public List<TaskEntity> findTaskByStatusId(int status_id) {
		String query = "SELECT * FROM tasks t WHERE status_id = ?; ";
		List<TaskEntity> listTask = new ArrayList<>();
		try (Connection connection = MysqlConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, status_id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					TaskEntity entity = new TaskEntity();
					entity.setId(resultSet.getInt("id"));
					entity.setName(resultSet.getString("name"));
					if (resultSet.getDate("start_date") != null) {
						entity.setStart_date(resultSet.getDate("start_date").toLocalDate());
					}
					if (resultSet.getDate("end_date") != null) {
						entity.setEnd_date(resultSet.getDate("end_date").toLocalDate());
					}
					entity.setUser(userRepository.findUserById(resultSet.getInt("user_id")));
					entity.setProject(projectRepository.findProjectById(resultSet.getInt("project_id")));
					entity.setStatus(statusRepository.findStatusById(resultSet.getInt("status_id")));
					listTask.add(entity);
				}
			}
		} catch (Exception e) {
			System.out.println("Error findTaskByStatusId " + e.getLocalizedMessage());
		}

		return listTask;
	}

	public List<TaskEntity> findTaskByProjectIdAndStatusId(int project_id, int status_id) {
		String query = "SELECT * FROM tasks t WHERE project_id = ? AND status_id = ?; ";
		Connection connection = MysqlConfig.getConnection();
		List<TaskEntity> listTask = new ArrayList<TaskEntity>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, project_id);
			preparedStatement.setInt(2, status_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				TaskEntity entity = new TaskEntity();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				if (resultSet.getDate("start_date") != null) {
					entity.setStart_date(resultSet.getDate("start_date").toLocalDate());
				}
				if (resultSet.getDate("end_date") != null) {
					entity.setEnd_date(resultSet.getDate("end_date").toLocalDate());
				}
				entity.setUser(userRepository.findUserById(resultSet.getInt("user_id")));
				entity.setProject(projectRepository.findProjectById(resultSet.getInt("project_id")));
				entity.setStatus(statusRepository.findStatusById(resultSet.getInt("status_id")));
				listTask.add(entity);
			}
		} catch (Exception e) {
			System.out.println("Error findTaskByProjectIdAndStatusId " + e.getLocalizedMessage());
		}
		return listTask;
	}

	public int insertTask(String name, LocalDate start_date, LocalDate end_date, int user_id, int project_id,
			int status_id) {
		int count = 0;
		try {
			String query = "INSERT INTO tasks (name, start_date, end_date, user_id, project_id, status_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?);";
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			if (start_date != null) {
				preparedStatement.setDate(2, Date.valueOf(start_date));
			} else {
				preparedStatement.setNull(2, Types.DATE);
			}
			if (end_date != null) {
				preparedStatement.setDate(3, Date.valueOf(end_date));
			} else {
				preparedStatement.setNull(3, Types.DATE);
			}
			preparedStatement.setInt(4, user_id);
			preparedStatement.setInt(5, project_id);
			preparedStatement.setInt(6, status_id);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error insertTask " + e.getLocalizedMessage());
		}
		return count;
	}

	public int modifyTaskById(int idTaskModify, String name, LocalDate start_date, LocalDate end_date, int user_id,
			int project_id, int status_id) {
		int count = 0;
		String query = "UPDATE tasks t SET t.name = ?, t.start_date = ?, t.end_date = ? ,"
				+ " t.user_id = ?, t.project_id = ?, t.status_id = ?  WHERE t.id = ?;";
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			if (start_date != null) {
				preparedStatement.setDate(2, Date.valueOf(start_date));
			} else {
				preparedStatement.setNull(2, Types.DATE);
			}
			if (end_date != null) {
				preparedStatement.setDate(3, Date.valueOf(end_date));
			} else {
				preparedStatement.setNull(3, Types.DATE);
			}
			preparedStatement.setInt(4, user_id);
			preparedStatement.setInt(5, project_id);
			preparedStatement.setInt(6, status_id);
			preparedStatement.setInt(7, idTaskModify);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error modifyTaskById " + e.getLocalizedMessage());
		}
		return count;
	}

	public int deleteTaskById(int id) {
		int count = 0;
		String query = "DELETE FROM tasks WHERE id = ?";
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error deleteTaskById " + e.getLocalizedMessage());
		}
		return count;
	}
}
