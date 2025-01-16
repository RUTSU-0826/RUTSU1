package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;

/**
 * Servlet implementation class DelMemberAjax
 */
@WebServlet("/DelMemberAjax")
public class DelMemberAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		MemberDao dao = new MemberDao();
		try {
			dao.DelMember(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
