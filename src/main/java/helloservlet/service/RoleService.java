package helloservlet.service;

import java.util.List;

import helloservlet.entity.RoleEntity;
import helloservlet.function.SetUTF8;
import helloservlet.repository.RoleRepository;

public class RoleService {
	private SetUTF8 SetUTF8 = new SetUTF8();
	private RoleRepository roleRepository = new RoleRepository();
	public List<RoleEntity> getAllRole() {
		return roleRepository.findAllRole();
	}
	public RoleEntity findRoleById(int id) {
		return roleRepository.findRoleById(id);
	}

	public boolean insertRole(String name, String description) {
		name = SetUTF8.setUTF8(name);
		description = SetUTF8.setUTF8(description);
		int count = roleRepository.insertRole(name, description);
		return count>0;
	}
	public boolean modifyRoleById(int id,String name, String description) {
		name = SetUTF8.setUTF8(name);
		description = SetUTF8.setUTF8(description);
		return roleRepository.modifyRoleById(id,name, description) > 0;
	}
	
	public boolean deleteRoleById(int id) {
		return roleRepository.deleteRoleById(id) > 0;
	}
}
