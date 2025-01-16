package dto;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.MemberDao;

/**
 * Servlet implementation class PointaddAjax
 */
@WebServlet("/PointaddAjax")
public class PointaddAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int point = Integer.parseInt(request.getParameter("point"));
		String id = request.getParameter("id");
		int addpoint = (int)(Math.random()*1000)+1;
		point += addpoint;
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
        obj1.put("addPoint",addpoint);
        obj1.put("newPoint",newPoint);
        out.println(obj1);
	}

}
