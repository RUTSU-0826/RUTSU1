package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.BoardDao;
import dto.BoardDto;


@WebServlet("/ExAjaxBoardListServlet")
public class ExAjaxBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum = Integer.parseInt(request.getParameter("page_num"));
		System.out.println("pageNum : " + pageNum);
		
		BoardDao dao = new BoardDao();
		ArrayList<BoardDto> list = null;
		try {
			list = dao.getBoardListByPageNumber(pageNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json"); // MIME
		PrintWriter out = response.getWriter();
		JSONArray array1 = new JSONArray();
		for(BoardDto dto : list) {
			JSONObject obj1 = new JSONObject();
			obj1.put("bno", dto.getBno());
			obj1.put("title", dto.getTitle());
			obj1.put("content", dto.getContent());
			obj1.put("writer", dto.getWriter());
			array1.add(obj1);
		}
		out.println(array1);
	}

}
