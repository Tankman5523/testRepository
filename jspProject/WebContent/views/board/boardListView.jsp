<%@page import="com.kh.common.model.vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.list-area{
		border: 1px solid white;
		text-align:center;
	}
	.list-area>tbody>tr:hover{
		background-color: gray;
		cursor:pointer;
	}
</style>
</head>
<body>
	<%@include file = "../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h1 align="center">일반 게시판</h1>
		<br><br>
		<%if(loginUser!=null){ %>
		<div align="center">
		<a href="<%=contextPath%>/insert.bo" class="btn btn-warning" >글 작성</a>
		</div>
		<%} %>
		
		<br>
		
		<table align="center" class="list-area">
			<thead>
				<tr>
					<th width="70">글번호</th>
					<th width="70">카테고리</th>
					<th width="300">제목</th>
					<th width="100">작성자</th>
					<th width="50">조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				<%for(int i=0;i<list.size();i++){ %>
				<tr>
					<td><%=list.get(i).getBoardNo() %></td>
					<td><%=list.get(i).getCategory() %></td>
					<td><%=list.get(i).getBoardTitle() %></td>
					<td><%=list.get(i).getBoardWriter() %></td>
					<td><%=list.get(i).getCount() %></td>
					<td><%=list.get(i).getCreateDate()%></td>
				</tr>
				<%} %>
			</tbody>
		</table>
		<br><br>
		
		<div align="center" class="paging-area">
		
		<%if(pi.getCurrentPage() != 1){ %>
			<button onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=pi.getCurrentPage()-1%>'">&lt;</button>
		<%} %>
		
		<%for(int i=pi.getStartPage(); i<=pi.getEndPage(); i++){ %>
			<!-- 내가 보고있는 페이지 버튼은 비활성화 하기 -->
			<%if(i != pi.getCurrentPage()){ %>
				<button onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=i%>';"><%=i %></button>
			<%}else{ %> <!-- 내가 보고있는 페이지와 페이징바 버튼의 수가 같아면 i와 currenPage -->
				<button disabled><%=i %></button>
			<%} %>
		
		<%} %>
			
		<%if(pi.getCurrentPage() != pi.getMaxPage()) {%>
			<button onclick="location.href='<%=contextPath%>/list.bo?currentPage=<%=pi.getCurrentPage()+1%>'">&gt;</button>
		<%} %>	
		
		</div>
	</div>
	<script>
		$(".list-area>tbody>tr").click(function(){
			//글번호 추출
			var bno=$(this).children().eq(0).text();
			location.href='<%=contextPath%>/detail.bo?bno='+bno;
			
		});
	</script>
	
</body>
</html>