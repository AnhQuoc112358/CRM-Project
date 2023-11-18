package helloservlet.service;

import java.time.LocalDate;
import java.util.List;

import helloservlet.entity.PercentageEntity;
import helloservlet.entity.TaskEntity;
import helloservlet.function.SetUTF8;
import helloservlet.repository.TaskRepository;

public class TaskService {
	private TaskRepository TaskRepository = new TaskRepository();
	private SetUTF8 SetUTF8 = new SetUTF8();

	public List<TaskEntity> getAllTask() {
		return TaskRepository.findAllTask();
	}

	public TaskEntity findTaskById(int id) {
		return TaskRepository.findTaskById(id);
	}

	public List<TaskEntity> findTaskByUserIdAndStatusId(int user_id, int status_id) {
		return TaskRepository.findTaskByUserIdAndStatusId(user_id, status_id);
	}
	
	public List<TaskEntity> findTaskByUserId(int user_id) {
		return TaskRepository.findTaskByUserId(user_id);
	}
	
	public List<TaskEntity> findTaskByStatusId(int status_id) {
		return TaskRepository.findTaskByStatusId(status_id);
	}

	public List<TaskEntity> findTaskByProjectIdAndStatusId(int project_id, int status_id) {
		return TaskRepository.findTaskByProjectIdAndStatusId(project_id, status_id);
	}

	public boolean insertTask(String name, String startDateStr, String endDateStr, int user_id, int project_id) {
		name = SetUTF8.setUTF8(name);
		LocalDate start_date = null;
		int status_id = 1; // Not started
		if (!startDateStr.isEmpty()) {
			start_date = LocalDate.parse(startDateStr);
			status_id = 2; // In progress
		}
		LocalDate end_date = null;
		if (!endDateStr.isEmpty()) {
			end_date = LocalDate.parse(endDateStr);
			status_id = 3; // Completed
		}
		int count = TaskRepository.insertTask(name, start_date, end_date, user_id, project_id, status_id);
		return count > 0;
	}

	public boolean modifyTaskById(int idTaskModify, String name, String startDateStr, String endDateStr, int user_id,
			int project_id) {
		name = SetUTF8.setUTF8(name);
		LocalDate start_date = null;
		int status_id = 1; // Not started
		if (!startDateStr.isEmpty()) {
			start_date = LocalDate.parse(startDateStr);
			status_id = 2; // In progress
		}
		LocalDate end_date = null;
		if (!endDateStr.isEmpty()) {
			end_date = LocalDate.parse(endDateStr);
			status_id = 3; // Completed
		}
		return TaskRepository.modifyTaskById(idTaskModify, name, start_date, end_date, user_id, project_id,
				status_id) > 0;
	}

	public boolean deleteTaskById(int id) {
		return TaskRepository.deleteTaskById(id) > 0;
	}

	public PercentageEntity percentTask(List<TaskEntity> notStarted, List<TaskEntity> inProgress,
			List<TaskEntity> completed) {
		PercentageEntity percentageEntity = new PercentageEntity();
		int countNotStarted = notStarted.size();
		percentageEntity.setCountTaskNotStarted(countNotStarted);
		int countInProgress = inProgress.size();
		percentageEntity.setCountTaskInProgress(countInProgress);
		int countCompleted = completed.size();
		percentageEntity.setCountTaskCompleted(countCompleted);
		int sumTask = countNotStarted + countInProgress + countCompleted;
		percentageEntity.setSumTask(sumTask);
		int percentNotStarted = 0;
		int percentInProgress = 0;
		int percentCompleted = 0;
		if (sumTask != 0) {
			percentNotStarted = countNotStarted * 100 / sumTask;
			percentInProgress = countInProgress * 100 / sumTask;
			percentCompleted = 100 - percentNotStarted - percentInProgress;
		}
		percentageEntity.setNotStarted(percentNotStarted);
		percentageEntity.setInProgress(percentInProgress);
		percentageEntity.setCompleted(percentCompleted);
		return percentageEntity;
	}
}
