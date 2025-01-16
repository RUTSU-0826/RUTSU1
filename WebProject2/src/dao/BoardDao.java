package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.BoardDto;
import dto.ReplyDto;

public class BoardDao {
	public Connection getConnection() throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "user1001";
		String pw = "pass1234";
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, id, pw);
		return conn;
	}
	// 게시물 전부 조회기능 with 페이지 번호
	// 파라미터 pageNum : 페이지번호(1~)
	public ArrayList<BoardDto>getBoardListByPageNumber(int pageNum) throws Exception {
		String sql = " SELECT b3.*" + 
				" FROM" + 
				"    (SELECT rownum rnum, b2.*" + 
				"    FROM" + 
				"        (SELECT *" + 
				"        FROM board1 b" + 
				"        ORDER BY bno DESC" + 
				"        )" + 
				"    b2)" + 
				" b3" + 
				" WHERE rnum>=? AND rnum<=? ";
		System.out.println(pageNum);
		int endRnum = pageNum * 15;
		int startRnum = endRnum - (15-1);
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startRnum);
		pstmt.setInt(2, endRnum);
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
	public int getCountBoard() throws Exception {
		String sql = "SELECT COUNT(*) FROM BOARD1";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		int ret = 0;
		if(rs.next()) {
			ret = rs.getInt(1);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return ret;
	}
//	// 게시글 전부 조회 기능.
//	public ArrayList<BoardDto> getAllBoardList() throws Exception {
//		String sql = "SELECT * FROM board1 ORDER BY bno DESC";
//		Connection conn = getConnection();
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		ResultSet rs = pstmt.executeQuery();
//		ArrayList<BoardDto> listRet = new ArrayList<BoardDto>();
//		while(rs.next()) {
//			int bno = rs.getInt("bno");
//			String title = rs.getString("title");
//			String content = rs.getString("content");
//			String writer = rs.getString("writer");
//			BoardDto dto = new BoardDto(bno, title, content, writer);
//			listRet.add(dto);
//		}
//		rs.close();
//		pstmt.close();
//		conn.close();
//		return listRet;
//	}
	
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
					+ " VALUES(SEQ_BOARD_COUNT.nextval, ?, ?, ?)";
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
	//댓글 작성
	public void registerReply(int bno, String writer, String content) throws Exception {
		String sql = "INSERT INTO reply1(rno, bno, content, writer) values (SEQ_RNO.nextval, ?,?,?)";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bno);
		pstmt.setString(2, content);
		pstmt.setString(3, writer);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	//댓글 갯수 조회
	//input : bno
	//output : 댓글개수
	public int getReplyCountByBno(int bno) throws Exception {
		String sql = " SELECT Count(RNO)" + 
				" FROM REPLY1" + 
				" WHERE bno = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bno);
		ResultSet rs = pstmt.executeQuery();
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return cnt;
	}
	//댓글 조회
	public ArrayList<ReplyDto> showReply(int bno) throws Exception {
		String sql = " SELECT RNO, CONTENT, WRITER, WRITEDATE" + 
				" FROM REPLY1" + 
				" WHERE BNO = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bno);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<ReplyDto> list = new ArrayList<ReplyDto>();
		while(rs.next()) {
			int rno = rs.getInt("RNO");
			String content = rs.getString("CONTENT");
			String writer = rs.getString("WRITER");
			String writedate = rs.getString("WRITEDATE");
			ReplyDto dto = new ReplyDto(rno, bno, content, writer, writedate);
			list.add(dto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}
	public static void main(String[] args) {
		BoardDao dao = new BoardDao();
		try {
			System.out.println(dao.getReplyCountByBno(1));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ArrayList<ReplyDto> list = null;
		try {
			list = dao.showReply(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(ReplyDto dto : list) {
			System.out.println(dto.getContent());
			System.out.println(dto.getWriter());
			System.out.println(dto.getWriteDate());
		}
//		try {
//			dao.registerReply(1, "LMS", "바보");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
////			dao.registerBoard("테스트 제목", "테스트 content", "YG");
////			dao.deleteBoardByBno(104);
//			dao.modifyBoard(102, "공유해줄게", "걱정하지마");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			System.out.println(dao.getCountBoard());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		ArrayList<BoardDto> list1 = null;
//		try {
//			list1 = dao.getBoardListByPageNumber(1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		for(BoardDto dto : list1) {
//			System.out.println(dto.getBno() + " / " + dto.getTitle() + " / " + dto.getContent() + " / " + dto.getWriter());
//		}
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






