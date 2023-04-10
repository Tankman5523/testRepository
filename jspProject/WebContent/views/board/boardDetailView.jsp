<%@page import="com.kh.board.model.vo.Attachment"%>
<%@page import="com.kh.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Board b = (Board)request.getAttribute("board");
	Attachment at = (Attachment)request.getAttribute("attachment");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer>table{
		border:1px solid white;
		width: 500px;
		height: 300px;
	}
	.outer>table td tr{
		border:1px solid white;
	}
</style>
</head>
<body>
	<%@include file="../common/menubar.jsp" %>
	<div class="outer">
		<h1 align="center">일반 게시판</h1>
		<table  align="center" border="1"  >
			<tr>
				<th width="150px">카테고리</th>
				<td width="350px"><%=b.getCategory() %></td>
				<th width="150px">제목</th>
				<td width="350px"><%=b.getBoardTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=b.getBoardWriter() %></td>
				<th>작성일</th>
				<td><%=b.getCreateDate() %></td>
			</tr>
			<tr>
				<th height="300px">내용</th>
				<td colspan=3><%=b.getBoardContent() %></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan=3>
				<%if(at!=null){ %>
					<a href="<%=contextPath + at.getFilePath()%>/<%=at.getChangeName()%>" download=<%=at.getOriginName() %>><%=at.getOriginName() %></a>
				<%}else{ %>
				<!-- 첨부파일이 없을경우 : 첨부파일이 없습니다. -->
					첨부파일이 없습니다.
				<%} %>
				</td>
			</tr>
		</table>
		<br><br>
		<%if(loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())){ %>
		<div align="center">
			<button onclick="location.href='<%=contextPath%>/update.bo?bno=<%=b.getBoardNo()%>'" class="btn btn-warning">수정하기</button>
			<button onclick="location.href='<%=contextPath%>/delete.bo?bno=<%=b.getBoardNo()%>'" class="btn btn-danger">삭제하기</button>
		</div>
		<%} %>
	</div>
	
</body>
</html>