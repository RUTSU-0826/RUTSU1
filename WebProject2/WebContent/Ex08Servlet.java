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
		System.out.println(request.getParameter("msg"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		//System.out.println(id + " , " + pw);
		MemberDao mDao = new MemberDao();
		boolean result = false;   // 로그인 성공 여부.
		try {
			result = mDao.loginCheck(id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(result);
		
		if(result) {
			// Ex08p2.jsp로 이동.
			RequestDispatcher rd = request.getRequestDispatcher("Ex08p2.jsp");
			rd.forward(request, response);
		} else {
			// Ex08.jsp로 이동.
			request.getRequestDispatcher("Ex08.jsp?fail=true").forward(request, response);
		}
	}
}







