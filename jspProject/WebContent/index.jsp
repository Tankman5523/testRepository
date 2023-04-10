<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.common.JDBCTemplate"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@include file = "views/common/menubar.jsp" %>
	
	<!-- 
		기본적으로 구현해야하는 기능
		C R U D 
		Create(insert) Read(select) Update Delete 
		
		--회원서비스 
		로그인 : R
		회원가입  : C
		정보변경 : U
		회원탈퇴 : U or D
		마이페이지 : R
		
		--게시판
		게시글 조회 : R
		게시글 작성 : C
		게시글 수정 : U
		게시글 삭제 : U or D
		
		--댓글
		댓글작성 : C
		댓글 조회 : R
		댓글 수정 : U
		댓글 삭제 : U or D
	 -->
</body>
</html>