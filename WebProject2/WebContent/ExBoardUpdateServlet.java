package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dto.BoardDto;

@WebServlet("/ExBoardUpdateServlet")
public class ExBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BoardDao bDao = new BoardDao();
		BoardDto boardDto = null;
		try {
			boardDto = bDao.getBoardDtoByBno(bno);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("boardDto", boardDto);
		RequestDispatcher rd = request.getRequestDispatcher("ExBoardModify.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bno = Integer.parseInt(request.getParameter("bno"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDao bDao = new BoardDao();
		try {
			bDao.modifyBoard(bno, title, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("ExBoardDetail.jsp?bno="+bno);
		rd.forward(request, response);
	}
}






