package dto;

public class MemberProfileDto {
	private String id;
	private String pw;
	private String name;
	private int point;
	private char admin;
	public MemberProfileDto(String id, String pw, String name, int point, char admin) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.point = point;
		this.admin = admin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public char getAdmin() {
		return admin;
	}
	public void setAdmin(char admin) {
		this.admin = admin;
	}
	
}
