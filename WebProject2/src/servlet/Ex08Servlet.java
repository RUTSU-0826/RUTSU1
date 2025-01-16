package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;

// ex. "컨텍스트패스/Ex08Servlet" 요청이 들어왔음!(get방식!)
@WebServlet("/Ex08Servlet")
public class Ex08Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		MemberDao mDao = new MemberDao();
		boolean result = false;
		try {
			result = mDao.loginCheck(id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result) {
			RequestDispatcher rd = request.getRequestDispatcher("Ex08p2.jsp");
			rd.forward(request, response);
		} else {
			request.getRequestDispatcher("Ex08.jsp?fail=true").forward(request, response);
		}
	}

}
