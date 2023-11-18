package helloservlet.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutService {
	public void logout(HttpServletResponse resp, HttpServletRequest req) {
		Cookie emailRemember = new Cookie("emailRemember", "");
		emailRemember.setMaxAge(0);
		resp.addCookie(emailRemember);
		Cookie passwordRemember = new Cookie("passwordRemember", "");
		passwordRemember.setMaxAge(0);
		resp.addCookie(passwordRemember);
		HttpSession session = req.getSession(false);
		if (session != null) {
	        session.invalidate();
	    }
	}
}
