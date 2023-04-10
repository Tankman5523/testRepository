<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<style>
        .outer{
            background-color: black;
            color: white;
            width: 1000px;
            margin: auto;
            margin-top: 50px;
        }
        #myPage-form table{
            margin: auto;
        }

        #myPage-form input{
            margin: 5px;
        }
        
        #changePwd,#deleteForm{
        	color : black;
        }
        
    </style>
</head>
<body>
	<%@include file="../common/menubar.jsp" %><!-- 해당 페이지에 있는 변수 사용하려면 ㅎ -->
		<!-- myPage.me -->
	<%
		String userId = loginUser.getUserId();
		String userName = loginUser.getUserName();
		String phone = (loginUser.getPhone()==null)?"":loginUser.getPhone();
		String email = (loginUser.getEmail()==null)?"":loginUser.getEmail();
		String address = (loginUser.getAddress()==null)?"":loginUser.getAddress();
		String interest = (loginUser.getInterest()==null)?"":loginUser.getInterest();
	
	%>
		
	
	<div class="outer">
        <br>
        <h2 align = "center">마이페이지</h2>
        
        <form action="<%=contextPath%>/update.me" method="post" id="myPage-form">
            <table>
                <tr>
                    <td>아이디</td>
                    <td><input type="text" name="userId" maxlength="12" readonly value="<%=userId %>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>이름</td>
                    <td><input type="text" name="userName" value="<%=userName%>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>전화번호</td>
                    <td><input type="text" name="phone" value="<%=phone%>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><input type="email" name="email" value="<%=email%>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td><input type="text" name="address" value="<%=address%>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>관심분야</td>
                    <td colspan="2">
                        <input type="checkbox" name="interest" id="sports" value="운동">
                        <label for="sports">운동</label>
                        <input type="checkbox" name="interest" id="movies" value="영화감상">
                        <label for="movies">영화감상</label>
                        <input type="checkbox" name="interest" id="board" value="보드">
                        <label for="board">보드</label>
                        <br>
                        <input type="checkbox" name="interest" id="cook" value="요리">
                        <label for="cook"></label>요리
                        <input type="checkbox" name="interest" id="game" value="게임">
                        <label for="game"></label>게임
                        <input type="checkbox" name="interest" id="book" value="독서">
                        <label for="book"></label>독서
                    </td>
                </tr>
            </table>
            <br><br>
            <div align="center">
                <button type="submit" class="btn btn-info">정보변경</button>
                <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#changePwd">비밀번호 변경</button>
                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원 탈퇴</button>
            </div>
        </form>
        
        <!-- 비밀번호 변경 모달 영역 -->
	<div class="modal" id="changePwd">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">비밀번호변경</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body" align="center">
	    	<!-- 현재 비밀번호 , 변경할 비밀번호 , 변경할 비밀번호 재입력 -->    	
				<form action="<%=contextPath%>/changePwd.me" method="post">
					<input type="hidden" name="userId" value="<%=userId%>">
					<table>
						<tr>
							<td>현재 비밀번호</td>
							<td><input type="password" name="userPwd" required></td>
						</tr>
						<tr>
							<td>변경할 비밀번호</td>
							<td><input type="password" name="updatePwd" required></td>
						</tr>
						<tr>
							<td>변경할 비밀번호 확인</td>
							<td><input type="password" id="chkPwd" required></td>
						</tr>
					</table>
					<br>
					
					<button type="submit" class="btn btn-warning" onclick="return validate();">비밀번호 변경</button>
				</form>
				
				<script>
					function validate(){
						var loginPwd = "<%=loginUser.getUserPwd()%>";
						var inputPwd = $("input[name=userPwd]").val();
						var updatePwd = $("input[name=updatePwd]").val();
						var chkPwd = $("#chkPwd").val();
						
						if(loginPwd==inputPwd){
							if(updatePwd!=chkPwd){
								alert("변경할 비밀번호와 확인이 일치하지 않습니다.");
								$("input[name=updatePwd]").select();
								return false;
							}
						}else {
							alert("현재 비밀번호가 일지하지 않습니다.");
							$("input[name=userPwd]").focus();
							return false;
						}
					}
				</script>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	   <!-- 회원탈퇴 모달 영역 -->
	<div class="modal" id="deleteForm">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">회원 탈퇴</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body" align="center" >
	        	<form action="<%=contextPath%>/deleteMem.me" method="post">
	        		<input type="hidden" name="userId" value="<%=userId%>">
	        		<table>
	        			<tr>
	        				<td>비밀번호 입력</td>
	        				<td><input type="password" name="userPwd2" required></td>
	        			</tr>
	        			
	        		
	        		</table>
	        		<br>
	        		<button type="submit" class="btn btn-danger" onclick="return validate2();">회원탈퇴</button>
	        	</form>
	      </div>
			<script>
					function validate2(){
						var loginPwd = "<%=loginUser.getUserPwd()%>";
						var inputPwd = $("input[name=userPwd2]").val();
						console.log(loginPwd);
						console.log(inputPwd);
						
						if(loginPwd!=inputPwd){
							alert("현재 비밀번호가 일치하지 않습니다.");
							$("input[name=userPwd]").focus();
							return false;
						}
						return confirm("정말로 삭제하시겠습니까?");
					}
			</script>
	
	
	
	    </div>
	  </div>
	</div>
    </div>
    <script>
    	$(function(){
    		var interest = "<%=interest%>";
    		//해당요소 순차적으로 접근하기
    		$("input[type=checkbox]").each(function(){
    				//console.log($(this).val());
    			//로그인된 사용자의 interest에 있는 요소가 있다면 checked하기
    			//취미가 : 영화감상 ,운동,독서.
    				//console.log(interest.search($(this).val()));
    				//console.log($(this).val());
    			//일치하지않으면 (찾지못하면) -1을 반환한다. 찾으면 해당 인덱스 반환
    			//선택한 요소를 찾아주려면 -1이 아닌 경우를 찾으면된다.
    			if(interest.search($(this).val())!=-1){
    				//일치하는 요소 체크하기
    				$(this).attr("checked",true);
    			}
    		})
    		
    	});	
    
    </script>
    
</body>
</html>