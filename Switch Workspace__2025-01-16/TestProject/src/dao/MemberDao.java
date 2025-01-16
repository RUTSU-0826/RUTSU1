package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.MemberProfileDto;



public class MemberDao {
	public Connection getConnection() throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "user1001";
		String pw = "pass1234";
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, id, pw);
		return conn;
	}
	public void MemberADD(String id, String pw, String name ) throws Exception {
		String sql = "INSERT INTO MEMBER VALUES(?,?,?,0,'N')";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		pstmt.setString(3, name);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	public boolean loginCheck(String id, String pw) throws Exception {
		String sql = "SELECT COUNT(*) FROM member WHERE id=? AND pw=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		ResultSet rs = pstmt.executeQuery();
		boolean result = false;
		if(rs.next()) {
			result = rs.getInt(1)==1;
		}
		rs.close();
		pstmt.close();
		conn.close();
		return result;
	}
	public ArrayList<MemberProfileDto> showMember() throws Exception {
		String sql = "SELECT " + 
				"    ID AS \"아이디\"," + 
				"    PW AS \"비밀번호\"," + 
				"    NAME AS \"이름\"," + 
				"    POINT AS \"포인트\"," + 
				"    ADMIN AS \"어드민\"" + 
				" 	FROM MEMBER";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<MemberProfileDto> list = new ArrayList<MemberProfileDto>();
		while(rs.next()) {
			String id = rs.getString("아이디"); 
			String pw = rs.getString("비밀번호"); 
			String name = rs.getString("이름"); 
			int point = rs.getInt("포인트"); 
			char admin = rs.getString("어드민").charAt(0); 
			MemberProfileDto dto = new MemberProfileDto(id, pw, name, point, admin);
			list.add(dto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}
	public MemberProfileDto showMemberIdx(String Id) throws Exception {
		String sql = "SELECT " + 
				"    ID AS \"아이디\"," + 
				"    PW AS \"비밀번호\"," + 
				"    NAME AS \"이름\"," + 
				"    POINT AS \"포인트\"," + 
				"    ADMIN AS \"어드민\"" + 
				" FROM MEMBER" + 
				" WHERE ID = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Id);
		ResultSet rs = pstmt.executeQuery();
		MemberProfileDto dto = null;
		if(rs.next()) {
			String id = rs.getString("아이디"); 
			String pw = rs.getString("비밀번호"); 
			String name = rs.getString("이름"); 
			int point = rs.getInt("포인트"); 
			char admin = rs.getString("어드민").charAt(0); 
			dto = new MemberProfileDto(id, pw, name, point, admin);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return dto;
	}
	public void payPoint(int point, String id) throws Exception {
		String sql = "UPDATE MEMBER SET POINT = ? WHERE ID = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, point);
		pstmt.setString(2, id);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	public void EditMember(String id, String pw, String name, int point) throws Exception {
		String sql = "UPDATE MEMBER SET PW = ?, NAME = ? ,POINT = ? WHERE ID = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pw);
		pstmt.setString(2, name);
		pstmt.setInt(3, point);
		pstmt.setString(4, id);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	public void DelMember(String id) throws Exception {
		String sql = "DELETE FROM MEMBER WHERE ID = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	public static void main(String[] args) {
			MemberDao dao = new MemberDao();
			try {
				dao.loginCheck("admin", "admin");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				dao.MemberADD("1", "2", "3");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			ArrayList<MemberProfileDto> list = null;
			try {
				list = dao.showMember();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for(MemberProfileDto dto : list) {
				System.out.println(dto.getName());
			}
			MemberProfileDto dto2 = null;
			try {
				dto2 = dao.showMemberIdx("admin");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out.println(dto2.getName());
			try {
				dao.payPoint(1, "admin");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				dao.EditMember("1", "3", "LMS", 1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				dao.DelMember("1");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
