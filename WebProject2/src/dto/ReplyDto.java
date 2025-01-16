package dto;

public class ReplyDto {
	private int rno;
	private int bno;
	private String content;
	private String writer;
	private String writeDate;
	public ReplyDto(int rno, int bno, String content, String writer, String writeDate) {
		this.rno = rno;
		this.bno = bno;
		this.content = content;
		this.writer = writer;
		this.writeDate = writeDate;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	
}
