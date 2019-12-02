package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SetDriver {
	private Connection conn;
	
	private SetDriver() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("1. Driver loading OK");
		setConnection();
	}		

	public static SetDriver getInstance(){
		return LazyHolder.INSTANCE;
	}
	
	//Class Loader의 특성을 활용하여 자동으로 동기화
	private static class LazyHolder{
		static final SetDriver INSTANCE = new SetDriver();
	}

	private void setConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("2. Connection OK");
	}

	public Connection getConnection() {
		return conn;
	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}