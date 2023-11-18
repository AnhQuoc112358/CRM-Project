package helloservlet.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helloservlet.entity.UserEntity;
import helloservlet.repository.UserRepository;

public class LoginService {
	private UserRepository userRepository = new UserRepository();

	public boolean checkLogin(String email, String password, String remember, HttpServletResponse resp,
			HttpServletRequest req) {
		UserEntity userNow = userRepository.findUserByEmailAndPassword(email, password);
		HttpSession session = req.getSession();
		session.setAttribute("userNow", userNow);
		System.out.println("usernow login: " + userNow);
		if (userNow != null) {
			if (remember != null) {
				Cookie emailRemember = new Cookie("emailRemember", email);
				emailRemember.setMaxAge(30 * 24 * 60 * 60);
				resp.addCookie(emailRemember);
				Cookie passwordRemember = new Cookie("passwordRemember", password);
				passwordRemember.setMaxAge(30 * 24 * 60 * 60);
				resp.addCookie(passwordRemember);
			}
			return true;
		} else {
			return false;
		}
	}
}
