<%@page import="java.sql.Date"%>
<%@page import="com.kh.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Notice n = (Notice)request.getAttribute("Detail");
	int noticeNo = n.getNoticeNo();
	String title = n.getNoticeTitle();
	String writer = n.getNoticeWriter();
	String content = n.getNoticeContent();
	Date createDate = n.getCreateDate();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#detail-area{
		border : 1px solid white;
	}

</style>
</head>
<body>

	<%@include file="../common/menubar.jsp" %>
	<div class="outer">
		<h2 align="center">공지사항 상세보기</h2>
		<table id="detail-area" align="center">
			<tr>
				<th width="70">제목</th>
				<td width="350" colspan="3"><%=title %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=writer %></td>
				<th>작성일</th>
				<td><%=createDate %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><p style="height:150px"><%=content %></p></td>
			</tr>
		</table>
		<br><br>
		<!-- 로그인한 회원의 아이디와 작성자의 아이디가 일치하면 수정 삭제 권한 부여 -->
		<%if(loginUser!=null &&loginUser.getUserId().equals(n.getNoticeWriter())){ %>
		<div align="center">
			<a href="<%=contextPath%>/update.no?nno=<%=noticeNo%>" class="btn btn-warning">수정하기</a>
			<a href="<%=contextPath%>/delete.no?nno=<%=noticeNo%>" class="btn btn-danger" >삭제하기</a> 
		</div>
		<%} %>
		
	</div>
	<!-- <script>
		$("#update").click(function(){
			var nno = <%=noticeNo%>;
			location.href='<%=contextPath%>/update.no?nno='+nno;
		});
		$("#delete").click(function(){
			var nno = <%=noticeNo%>;
			location.href='<%=contextPath%>/delete.no?nno='+nno;
		});
	</script>  -->
	
</body>
</html>