package domain;


public class Board {
	
	private int num;
	
	private String title;
	private String content;
	private String writer;
	private String now; // 작성시간
	
	
	public Board() {
	}
	
	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}

}