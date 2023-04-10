package com.kh.common;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	public static Connection getConnection() {
		
		Properties prop = new Properties();
		
		//읽어들이고자하는 driver.properties 파일의 경로를 알아내서 대입해야한다.
		String filePath= JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();
//		System.out.println(filePath);
		Connection conn = null;
		
		try {
			prop.load(new FileInputStream(filePath));
			
			//1. jdbc driver 등록
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), 
											   prop.getProperty("username"), 
											   prop.getProperty("password"));
			
			//오토커밋기능을 끄지 않으면 오류나도 DB가 자동으로 커밋됨.
			conn.setAutoCommit(false);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//2.전달받은 Connection객체를 가지고 commit 해주는 메소드
	
	public static void commit(Connection conn) {
		
		try {
			if(conn!=null&&!conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//3. 롤백 메소드
	public static void rollback(Connection conn) {
		try {
			if(conn!=null&&!conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//4. 커넥션 객체 반납 메소드
	public static void close(Connection conn) {
		try {
			if(conn!=null&&!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//5.Statement 반납 메소드
	public static void close(Statement stmt) {
		try {
			if(stmt!=null&&!stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//6.ResultSet 반납 메소드
	public static void close(ResultSet rset) {
		try {
			if(rset!=null&&!rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
