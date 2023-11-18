package helloservlet.service;

import java.time.LocalDate;
import java.util.List;

import helloservlet.entity.ProjectEntity;
import helloservlet.function.SetUTF8;
import helloservlet.repository.ProjectRepository;

public class ProjectService {
	private ProjectRepository ProjectRepository = new ProjectRepository();
	private SetUTF8 SetUTF8 = new SetUTF8();
	public List<ProjectEntity> getAllProject() {
		return ProjectRepository.findAllProject();
	}
	public ProjectEntity findProjectById(int id) {
		return ProjectRepository.findProjectById(id);
	}
	public boolean insertProject(String name, String startDateStr, String endDateStr) {
		name = SetUTF8.setUTF8(name);
		LocalDate start_date = null;
		if (!startDateStr.isEmpty()) {
			start_date = LocalDate.parse(startDateStr);
		}
		LocalDate end_date = null;
		if (!endDateStr.isEmpty()) {
			end_date = LocalDate.parse(endDateStr);
		}
		int count = ProjectRepository.insertProject(name, start_date, end_date) ;
		return count>0;
	}
	public boolean modifyProjectById(int idProjectModify,String name, String startDateStr, String endDateStr) {
		name = SetUTF8.setUTF8(name);
		LocalDate start_date = null;
		if (!startDateStr.isEmpty()) {
			start_date = LocalDate.parse(startDateStr);
		}
		LocalDate end_date = null;
		if (!endDateStr.isEmpty()) {
			end_date = LocalDate.parse(endDateStr);
		}
		return ProjectRepository.modifyProjectById(idProjectModify,name, start_date, end_date) > 0;
	}
	
	public boolean deleteProjectById(int id) {
		return ProjectRepository.deleteProjectById(id) > 0;
	}
}
