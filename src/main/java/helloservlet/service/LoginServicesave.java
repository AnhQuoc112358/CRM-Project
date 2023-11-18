//package helloservlet.service;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletResponse;
//
//import helloservlet.entity.UserEntity;
//import helloservlet.repository.UserRepository;
//
//public class LoginServicesave {
//	private static final LoginServicesave instance = new LoginServicesave();
//	private UserRepository userRepository = new UserRepository();
//    private UserEntity userNow;
//    private LoginServicesave() {
//    }
//    public static LoginServicesave getInstance() {
//        return instance;
//    }
//	public boolean checkLogin(String email, String password, String remember, HttpServletResponse resp) {
//		userNow = userRepository.findUserByEmailAndPassword(email, password);
//		if (userNow != null) {
//			System.out.println("email user from login " + userNow.getEmail());
//			Cookie emailNow = new Cookie("emailNow", email);
//			emailNow.setMaxAge(-1);
//			resp.addCookie(emailNow);
//			Cookie passwordNow = new Cookie("passwordNow", password);
//			passwordNow.setMaxAge(-1);
//			resp.addCookie(passwordNow);
//			System.out.println("remember " + remember);
//			if (remember != null) {
//				Cookie emailRemember = new Cookie("emailRemember", email);
//				emailRemember.setMaxAge(30*24*60*60);
//				resp.addCookie(emailRemember);
//				Cookie passwordRemember = new Cookie("passwordRemember", password);
//				passwordRemember.setMaxAge(30*24*60*60);
//				resp.addCookie(passwordRemember);
//			}
//			return true;
//		} else {
//			return false;
//		}
//	}
//	public UserEntity getUserNow() {
//        return userNow;
//    }
//}
