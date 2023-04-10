<%@page import="com.kh.board.model.vo.Board"%>
<%@page import="com.kh.board.model.vo.Attachment"%>
<%@page import="com.kh.board.model.vo.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Category> clist=(ArrayList<Category>)request.getAttribute("clist");
	Attachment at = (Attachment)request.getAttribute("attachment");
	Board b = (Board)request.getAttribute("board");
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정페이지</title>
<style>
	
	#update-form>table{
		border : 1px solid black;
	
	}
	
	#update-form input,textarea{
		width:100%;
		box-sizing : border-box;
	}

</style>
</head>
<body>
	<%@ include file = "../common/menubar.jsp" %>
    
	<div class="outer">
		<h2 align="center">게시글 수정</h2>
        <!-- 카테고리,제목,내용,첨부파일, 작성자 번호 (작성한 해당 유저만 작성가능 ) -->
        <form action="<%=contextPath%>/update.bo" method ="post" id="update-form" enctype ="multipart/form-data">
            <!-- 카테고리 테이블에서 조회해온 카테고리 목록 선택 상자 만들기  -->
            
            <input type="hidden" name="bno" value="<%=b.getBoardNo()%>">
            
            <!-- 카테고리 기존것과 일치하는 것으로 설정 -->
            <script>
           	$(function(){
           		//option에 있는 text와 조회해온 게시글 카테고리 일치하는지 찾아내어 선택되어있게 작업하기
           		$("#update-form option").each(function(){
           			//현재 접근된 요소객체의 text와 조회해온 카테고리가 같다면
           			if($(this).text()=="<%=b.getCategory()%>"){
           				//해당 요소를 선택되어있도록 만들기
           				$(this).attr("selected",true);
           			}
           		});
           	});
       		</script>
            <table align = "center">
                <tr>
                    <th width ="100">카테고리</th>
                    <td width = "500">
                        <select name="category" > <!-- value는 숫자 조회시엔 이름-->
                            <%for(Category c : clist){ %>
                            <option value="<%=c.getCategoryNo()%>" ><%=c.getCategoryName() %></option>
                            
                            <%} %>
                        </select>
                    </td>
                </tr>
           
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="title" value="<%=b.getBoardTitle() %>" required></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea name="content" cols="30" rows="10" required style="resize:none"><%=b.getBoardContent() %></textarea>
                    </td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td>
                    	<!-- 기존 파일을 어떻게 넣어둘 것인가... -->
                    	<%if(at!=null){ %>
                    	<!-- 기존 첨부파일이 있었을 경우 수정할 때 첨부파일 정보를 보내야한다. -->
                    	<!-- 파일번호,변경된 파일명 전달하기 -->
                    	<a href="<%=contextPath + at.getFilePath()%>/<%=at.getChangeName()%>" ><%=at.getOriginName() %></a>
                    	<!-- <button onclick="">파일삭제</button> -->
                    	<input type="hidden" name="fileNo" value="<%=at.getFileNo()%>">
                    	<input type="hidden" name="originFileName" value="<%=at.getChangeName()%>"><!-- 기존 체인지네임. -->
                    	<%}%>
                    	<input type="file" name="reUpfile">
                    </td>
                </tr>
				
            </table>
            <br>
			<div align ="center">
	            <button type="submit">게시글 수정</button>
	            <button type="button" onclick="history.back();">뒤로가기</button>
			</div>
        </form>
     </div>
      
</body>
</html>