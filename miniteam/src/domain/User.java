package domain;

public class User {
	
	private String id;
	private String name;
	private String password;
	private String num;
	private boolean doctor = false;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String id, String name, String password, String num) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.num = num;
	}
	
	public User(String id, String name, String password, String num, boolean doctor) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.num = num;
		this.doctor = doctor;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNum() {
		return num;
	}
	
	public void setNum(String num) {
		this.num = num;
	}
	
	public boolean isDoctor() {
		return doctor;
	}
	
	public void setDoctor(boolean doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", 이름=" + name + ", password=" + password + ", 핸드폰번호=" + num + "]";
	}

	
	
}