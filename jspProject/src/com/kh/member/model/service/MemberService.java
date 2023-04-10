package com.kh.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {

	public Member loginMember(String userId, String userPwd) {
		//커넥션객체로 디비에 접속
		Connection conn = JDBCTemplate.getConnection();
		
		Member m =new MemberDao().loginMember(conn,userId,userPwd);
		
		//조회는 commit / rollback 필요 없으니 자원반납
		JDBCTemplate.close(conn);
		
		return m;
	
	}

	public int insertMember(String userId, String userPwd, String userName, String phone, String email,
			String address, String interest) {
		Connection conn=JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMember(conn,userId,userPwd,userName,phone,email,address,interest);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
		
	}
	public int insertMember(Member m) throws SQLException{
		Connection conn=JDBCTemplate.getConnection();
		int result = new MemberDao().insertMember(conn,m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Member updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().updateMember(conn,m);
		//변경된 회원 정보를 세션에 담아야하기 때문에 다시 조회해오기
		Member updateMem = null; //변경된 회원정보 담을 객체변수
		if(result>0) {
			JDBCTemplate.commit(conn);
			//갱신된 정보 조회해오기
			updateMem= new MemberDao().selectMember(conn,m.getUserId());
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return updateMem;
	}

	public Member changePwd(String userPwd, String updatePwd, String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().changePwd(conn,userPwd,updatePwd,userId);
		Member updateMem=null;
		if(result>0) {
			JDBCTemplate.commit(conn);
			updateMem= new MemberDao().selectMember(conn,userId);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return updateMem;
	}

	public int deleteMember(String userId, String userPwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().deleteMember(conn,userId,userPwd);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
