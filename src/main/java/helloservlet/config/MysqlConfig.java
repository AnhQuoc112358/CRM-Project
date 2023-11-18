package helloservlet.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConfig {
	public static Connection getConnection() {
		try {
//			Khai báo Driver sử dụng cho JDBC (từ khóa driver class.forname)
			Class.forName("com.mysql.cj.jdbc.Driver");
//			Khái báo thông tin csdl mà JDBC sẽ kết nối tới
			return DriverManager.getConnection("jdbc:mysql://localhost:3307/crm_app", "root", "Neversaynever1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error connect database " + e.getLocalizedMessage());
		}
		return null;
	}
}
