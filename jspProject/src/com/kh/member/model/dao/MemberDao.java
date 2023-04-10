package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() { //Dao객체가 생성되면 query문 읽어올수 있도록...
		String filePath = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	//로그인할 회원 정보 조회 메소드
	public Member loginMember(Connection conn,String userId,String userPwd) {
		//SELECT문 - ResultSet객체 필요 - 하나 또는 0개 / 하나의 유저정보가 나온다면 Member객체로 담아서 돌아가기
		Member m = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,userId);
			pstmt.setString(2,userPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("USER_NO")
							  ,rset.getString("USER_ID")
							  ,rset.getString("USER_PWD")
							  ,rset.getString("USER_NAME")
							  ,rset.getString("PHONE")
							  ,rset.getString("EMAIL")
							  ,rset.getString("ADDRESS")
							  ,rset.getString("INTEREST")
							  ,rset.getDate("ENROLL_DATE")
							  ,rset.getDate("MODIFY_DATE")
							  ,rset.getString("STATUS")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return m;
	}

	public int insertMember(Connection conn, String userId, String userPwd, String userName, String phone,
			String email,String address, String interest) {
		
		int result=0;
		String sql=prop.getProperty("insertMember");
		PreparedStatement pstmt = null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			pstmt.setString(3, userName);
			pstmt.setString(4, phone);
			pstmt.setString(5, email);
			pstmt.setString(6, address);
			pstmt.setString(7, interest);
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	//객체로 받는 메소드
	public int insertMember(Connection conn, Member m) {
		int result=0;
		String sql=prop.getProperty("insertMember");
		PreparedStatement pstmt = null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getInterest());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	//정보수정 메소드
	public int updateMember(Connection conn, Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getInterest());
			pstmt.setString(6, m.getUserId());
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}
	//아이디로 회원정보 조회메소드
	public Member selectMember(Connection conn, String userId) {
		
		ResultSet rset=null;
		PreparedStatement pstmt= null;
		String sql = prop.getProperty("selectMember");
		Member m = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("USER_NO")
						  ,rset.getString("USER_ID")
						  ,rset.getString("USER_PWD")
						  ,rset.getString("USER_NAME")
						  ,rset.getString("PHONE")
						  ,rset.getString("EMAIL")
						  ,rset.getString("ADDRESS")
						  ,rset.getString("INTEREST")
						  ,rset.getDate("ENROLL_DATE")
						  ,rset.getDate("MODIFY_DATE")
						  ,rset.getString("STATUS"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}

	public int changePwd(Connection conn, String userPwd, String updatePwd, String userId) {
			
		int result=0;
		PreparedStatement pstmt=null;
		String sql = prop.getProperty("changePwd");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, userId);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}

	public int deleteMember(Connection conn, String userId, String userPwd) {
		
		int result=0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
}
