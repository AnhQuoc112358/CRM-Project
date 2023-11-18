package helloservlet.entity;

public class PercentageEntity {
	private int notStarted;
	private int inProgress;
	private int completed;
	private int countTaskNotStarted;
	private int countTaskInProgress;
	private int countTaskCompleted;
	private int sumTask;
	public int getSumTask() {
		return sumTask;
	}
	public void setSumTask(int sumTask) {
		this.sumTask = sumTask;
	}
	public int getCountTaskNotStarted() {
		return countTaskNotStarted;
	}
	public void setCountTaskNotStarted(int countTaskNotStarted) {
		this.countTaskNotStarted = countTaskNotStarted;
	}
	public int getCountTaskInProgress() {
		return countTaskInProgress;
	}
	public void setCountTaskInProgress(int countTaskInProgress) {
		this.countTaskInProgress = countTaskInProgress;
	}
	public int getCountTaskCompleted() {
		return countTaskCompleted;
	}
	public void setCountTaskCompleted(int countTaskCompleted) {
		this.countTaskCompleted = countTaskCompleted;
	}
	public int getNotStarted() {
		return notStarted;
	}
	public void setNotStarted(int notStarted) {
		this.notStarted = notStarted;
	}
	public int getInProgress() {
		return inProgress;
	}
	public void setInProgress(int inProgress) {
		this.inProgress = inProgress;
	}
	public int getCompleted() {
		return completed;
	}
	public void setCompleted(int completed) {
		this.completed = completed;
	}

}
