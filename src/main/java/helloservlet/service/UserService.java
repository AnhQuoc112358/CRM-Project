package helloservlet.service;

import java.util.List;

import helloservlet.entity.UserEntity;
import helloservlet.function.SetUTF8;
import helloservlet.repository.UserRepository;

public class UserService {
	private UserRepository userRepository = new UserRepository();
	private SetUTF8 SetUTF8 = new SetUTF8();

	public List<UserEntity> getAllUser() {
		return userRepository.findAllUser();
	}

	public UserEntity findUserByEmailAndPassword(String email, String password) {
		return userRepository.findUserByEmailAndPassword(email,password);
	}
	
	public UserEntity findUserById(int id) {
		return userRepository.findUserById(id);
	}

	public boolean insertUser(String email, String password, String username, String firstname, String lastname,
			String phone_number, String country, int role_id) {
		username = SetUTF8.setUTF8(username);
		firstname = SetUTF8.setUTF8(firstname);
		lastname = SetUTF8.setUTF8(lastname);
		country = SetUTF8.setUTF8(country);
		int count = userRepository.insertUser(email, password, username, firstname, lastname, phone_number, country,
				role_id);
		return count > 0;
	}

	public boolean modifyUserById(int idUserModify, String email, String password, String username, String firstname,
			String lastname, String phone_number, String country, int role_id) {
		username = SetUTF8.setUTF8(username);
		firstname = SetUTF8.setUTF8(firstname);
		lastname = SetUTF8.setUTF8(lastname);
		country = SetUTF8.setUTF8(country);
		return userRepository.modifyUserById(idUserModify, email, password, username, firstname, lastname, phone_number,
				country, role_id) > 0;
	}

	public boolean deleteUserById(int id) {
		return userRepository.deleteUserById(id) > 0;
	}
}
