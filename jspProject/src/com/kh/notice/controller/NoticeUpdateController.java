package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateController
 */
@WebServlet("/update.no")
public class NoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//우회
		
		String noticeNo = request.getParameter("nno");
		
		Notice n = new NoticeService().selectNotice(noticeNo);
		
		
		request.setAttribute("notice", n);
		
		request.getRequestDispatcher("views/notice/noticeUpdateForm.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		int noticeNo =Integer.parseInt(request.getParameter("noticeNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Notice n = new Notice();
		n.setNoticeNo(noticeNo);
		n.setNoticeTitle(title);
		n.setNoticeContent(content);
		
		int result=new NoticeService().updateNotice(n);
		
		if(result>0) {
			session.setAttribute("alertMsg","공지사항 수정이 완료되었습니다.");
//			response.sendRedirect(request.getContextPath()+"/list.no");
			response.sendRedirect(request.getContextPath()+"/detail.no?nno="+noticeNo);
		}else {
			request.setAttribute("errorMsg", "공지사항 수정 실패!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
	}

}
