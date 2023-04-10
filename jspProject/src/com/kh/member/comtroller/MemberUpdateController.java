package com.kh.member.comtroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//변경된 정보 수정하기
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interests = request.getParameterValues("interest");
		String interest ="";
		if(interests!=null) {
			interest = String.join(",", interests); 
		}
		
		Member m = new Member(userId,userName, phone, email, address, interest); 
		
		Member updateMem=new MemberService().updateMember(m);
		
		HttpSession session = request.getSession();
		//수정 완료 후 성공시 정보변경 완료되었습니다. 알림메시지 후ㅡ 마이페이지로 이동 (변경정보 적용)(재요청)
		if(updateMem!=null) {
			//완료는 됬는데...로그아웃했다가 다시 로그인해야 뷰 화면에서 정보가 갱신되어있음.
			//loginUser 다시 세팅해야함.
			 
			session.setAttribute("alertMsg", "회원정보 변경이 완료되었습니다.");
			session.setAttribute("loginUser", updateMem); //동일키값으로 작성하면 갱신된다.
			//루트 뒤에 마이페이지 요청 매핑주소 넣기
			response.sendRedirect(request.getContextPath()+"/myPage.me");
		}else {//실패시 에러페이지로 포워딩(위임)
			request.setAttribute("errorMsg", "회원수정에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}
	}

}
