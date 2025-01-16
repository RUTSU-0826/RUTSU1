package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.BoardDto;

public class BoardDao {
	public Connection getConnection() throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "user1001";
		String pw = "pass12345";
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, id, pw);
		return conn;
	}
	
	// 게시글 전부 조회 기능.
	public ArrayList<BoardDto> getAllBoardList() throws Exception {
		String sql = "SELECT * FROM board1 ORDER BY bno DESC";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<BoardDto> listRet = new ArrayList<BoardDto>();
		while(rs.next()) {
			int bno = rs.getInt("bno");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String writer = rs.getString("writer");
			BoardDto dto = new BoardDto(bno, title, content, writer);
			listRet.add(dto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return listRet;
	}
	
	// 게시글 상세보기
	public BoardDto getBoardDtoByBno(int bno) throws Exception {
		String sql = "SELECT title, content, writer FROM board1 WHERE bno=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bno);
		ResultSet rs = pstmt.executeQuery();
		BoardDto dtoRet = null;
		if(rs.next()) {
			String title = rs.getString("title");
			String content = rs.getString("content");
			String writer = rs.getString("writer");
			dtoRet = new BoardDto(bno, title, content, writer);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return dtoRet;
	}
	
	// 게시글 등록
	public void registerBoard(String title, String content, String writer) throws Exception {
		String sql = "INSERT INTO board1(bno, title, content, writer) "
					+ " VALUES(seq_board.nextval, ?, ?, ?)";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, writer);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	
	// 게시글 삭제하기
	public void deleteBoardByBno(int bno) throws Exception {
		String sql = "DELETE FROM board1 WHERE bno=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bno);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	
	// 게시글 수정하기
	public void modifyBoard(int bno, String title, String content) throws Exception {
		String sql = "UPDATE board1 SET title=?, content=? WHERE bno=?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setInt(3, bno);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	
	public static void main(String[] args) {
		BoardDao dao = new BoardDao();
		try {
//			dao.registerBoard("테스트 제목", "테스트 content", "YG");
//			dao.deleteBoardByBno(104);
			dao.modifyBoard(102, "공유해줄게", "걱정하지마");
		} catch (Exception e) {
			e.printStackTrace();
		}
//		BoardDto dto = null;
//		try {
//			dto = dao.getBoardDtoByBno(103);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(dto.getTitle() + " / " + dto.getContent());
		
//		ArrayList<BoardDto> list1 = null;
//		
//		try {
//			list1 = dao.getAllBoardList();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		for(BoardDto dto : list1) {
//			System.out.println(dto.getBno() + " / " + dto.getTitle() + " / " + dto.getContent() + " / " + dto.getWriter());
//		}
	} 
}






