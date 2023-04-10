package com.kh.notice.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.notice.model.vo.Notice;


public class NoticeDao {
	
private Properties prop = new Properties();
	
	public NoticeDao() { 
		String filePath = NoticeDao.class.getResource("/sql/notice/notice-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public ArrayList<Notice> selectList(Connection conn) {
		
		Statement stmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectList");
		
		ArrayList<Notice> list = new ArrayList<Notice>();
		
		try {
			stmt= conn.createStatement();
			rset=stmt.executeQuery(sql);
			
			while(rset.next()) {
				list.add(new Notice(rset.getInt("NOTICE_NO")
							,rset.getString("NOTICE_TITLE")
							,rset.getString("USER_ID")
							,rset.getInt("COUNT")
							,rset.getDate("CREATE_DATE")
							));
				
//				n=new Notice(rset.getInt("NOTICE_NO")
//							,rset.getString("NOTICE_TITLE")
//							,rset.getString("NOTICE_CONTENT")
//							,rset.getString("NOTICE_WRITER")
//							,rset.getInt("COUNT")
//							,rset.getDate("CREATE_DATE")
//							,rset.getString("STATUS"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return list;
	}

	public int insertNotice(Connection conn, String noticeTitle, String noticeContent, String userNo) {
		PreparedStatement pstmt = null;
		int result=0;
		String sql = prop.getProperty("insertNotice");
		//int userNo2= (Int)userNo;
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, noticeTitle);
			pstmt.setString(2, noticeContent);
			// pstmt.setString(3, userNo);//내부 형변환으로 인해 NUMBER 타입에도 들어감
			pstmt.setInt(3,Integer.parseInt(userNo));//NUMBER타입이기때문에 int로 형변환 후 넘기기
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Notice selectNotice(Connection conn, String noticeNo) {
		
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql=prop.getProperty("selectNotice");
		Notice n = null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(noticeNo));
			
			rset=pstmt.executeQuery();
			if(rset.next()) {
				n = new Notice(rset.getInt("NOTICE_NO")
							  ,rset.getString("NOTICE_TITLE")
							  ,rset.getString("NOTICE_CONTENT")
							  ,rset.getString("USER_ID")
							  ,rset.getDate("CREATE_DATE")
						);
			}
			//System.out.println(n);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return n;
		
		
	}

	public int countNotice(Connection conn, String noticeNo) {
		
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("countNotice");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(noticeNo));
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateNotice(Connection conn, Notice n) {
		
		PreparedStatement pstmt=null;
		int result = 0;
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
		
	}

	public int deleteNotice(Connection conn, int noticeNo) {

		PreparedStatement pstmt=null;
		int result=0;
		String sql = prop.getProperty("deleteNotice");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
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
