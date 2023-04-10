package com.kh.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {

	public ArrayList<Notice> selectList() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Notice> list =new NoticeDao().selectList(conn);
		//조회구문이기 때문에 트랜젝션 처리할 필요 없음
		JDBCTemplate.close(conn);
		return list; 
		
		
	}

	public int insertNotice(String noticeTitle, String noticeContent,String userNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result=new NoticeDao().insertNotice(conn,noticeTitle,noticeContent,userNo);
		ArrayList<Notice> list = null;
		if(result>0) {
			JDBCTemplate.commit(conn);
			//list =new NoticeDao().selectList(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		//return list;
		return result;
	}

	public Notice selectNotice(String noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		Notice n = new NoticeDao().selectNotice(conn,noticeNo);
		int result=new NoticeDao().countNotice(conn,noticeNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return n;
		
		
	}

	public int updateNotice(Notice n) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result=new NoticeDao().updateNotice(conn,n);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteNotice(int noticeNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().deleteNotice(conn,noticeNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	
}
