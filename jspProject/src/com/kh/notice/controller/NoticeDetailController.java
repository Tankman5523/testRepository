package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailController
 */
@WebServlet("/detail.no")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//공지사항 하나의 정보를 조회해와서 request에 담아 위임하기
		//보내진 view에서 데모데이터 대신 담아온 notice 정보 출력하기
		
		//글번호 추출
		String noticeNo = request.getParameter("nno");
		
		//System.out.println(userNo);
		
		Notice n = new NoticeService().selectNotice(noticeNo);
		
		//System.out.println(n);
		if(n!=null) {
		request.setAttribute("Detail", n);
		
		request.getRequestDispatcher("views/notice/noticeDetailView.jsp").forward(request, response);
		}else {
			request.setAttribute("errorMsg", "공지사항 조회 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
