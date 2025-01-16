package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.MemberDao;
import dto.MemberProfileDto;


@WebServlet("/PointAjax")
public class PointAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int point = Integer.parseInt(request.getParameter("point"));
		String id = request.getParameter("id");
		MemberDao dao = new MemberDao();
		try {
			dao.payPoint(point, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MemberProfileDto dto = null; 
		try {
			dto = dao.showMemberIdx(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int newPoint = dto.getPoint();
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        JSONObject obj1 = new JSONObject();
        obj1.put("newPoint",newPoint);
        out.println(obj1);
	}

}
