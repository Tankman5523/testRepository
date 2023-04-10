package com.kh.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("views/notice/noticeEnrollForm.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String noticeTitle = request.getParameter("title");
		String noticeContent = request.getParameter("content");
		//String userNo = String.valueOf(((Member)session.getAttribute("loginUser")).getUserNo());
		String userNo=request.getParameter("userNo");
				
		//데이터베이스에 작성한 내용 등록시키고
		//ArrayList<Notice> list = new NoticeService().insertNotice(noticeTitle,noticeContent);
		int result = new NoticeService().insertNotice(noticeTitle,noticeContent,userNo);
		
		/* 객체로 들고가기 (생성자 사용 X setter로 집어넣어서...)
		 * Notice n = new Notice();
		 * n.setNoticeTitle(title);
		 * n.setNoticeContent(content);
		 * n.setNoticeWriter(userNo);
		 * 
		 * */
		
		//성공시 알림메시지로 공지등록완료, 공지사항 목록으로 이동(재요청)
//		if(list!=null) {
//			request.setAttribute("AlertMsg", "공지등록 완료");
//			request.setAttribute("list", list);
//			response.sendRedirect(request.getContextPath()+"/list.no");
//		}
		if(result>0) {
			session.setAttribute("alertMsg", "공지등록 완료");
			
			response.sendRedirect(request.getContextPath()+"/list.no");
		}
		//실패시 에러페이지로 이동(공지사항 작성 실패) 메시지(위임)
		else {
			request.setAttribute("errorMsg", "공지사항 작성 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

}
