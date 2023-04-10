package com.kh.board.comtroller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;

/**
 * Servlet implementation class BoardDeleteController
 */
@WebServlet("/delete.bo")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		Attachment at = new BoardService().selectAttachment(boardNo);
		//첨부파일에 대한 조건 처리가 어렵다...
		int result = 0;
		if(at!=null) {
			result = new BoardService().deleteBoard(boardNo);
				//파일 상태변경 방법
			result = new BoardService().deleteAttachment(boardNo);
				//파일 자체를 삭제하는 방법
			//String savePath = request.getSession().getServletContext().getRealPath("/resources/board_files/");
			//new File(savePath+at.getChangeName()).delete();
			
			
		}else {
			result = new BoardService().deleteBoard(boardNo);
		}
		
		if(result>0) {//삭제성공
			request.getSession().setAttribute("alertMsg", "게시글을 삭제하였습니다.");
			response.sendRedirect(request.getContextPath()+"/list.bo?currentPage=1");
		}else {//삭제실패
			request.setAttribute("errorMsg", "게시글 삭제 실패");
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
