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
 * Servlet implementation class MemberChangePwd
 */
@WebServlet("/changePwd.me")
public class MemberChangePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberChangePwd() {
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
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		//세션에서 userId를 가져오는 방법도 있다.
		//String userId = ((Member)session.getAttribute("loginUser")).getUserId();
		
		
		//userId랑 updatePwd만 들고가도된다.
		String userPwd=request.getParameter("userPwd");
		String updatePwd=request.getParameter("updatePwd");
		String userId=request.getParameter("userId");
		
		Member result = new MemberService().changePwd(userPwd,updatePwd,userId);
		//result == updateMem
		if(result!=null) {
			session.setAttribute("alertMsg", "비밀번호 변경이 완료되었습니다.");
			session.setAttribute("loginUser", result); //동일키값으로 작성하면 갱신된다.
			//재로그인 시키려면 session.removeAttribute("loginUser");
			response.sendRedirect(request.getContextPath()+"/myPage.me");
		}else {
			request.setAttribute("errorMsg", "비밀번호 변경에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
			
		}
	}

}
