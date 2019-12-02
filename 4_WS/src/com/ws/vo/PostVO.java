package com.ws.vo;

public class PostVO {
	private int postId;
	private String title;
	private String nickname;
	private String fileName;
	private String content;
	private String postDate;
	private String ticketNumber;
	private String email;
	private String good;
	private String click;
	
	public PostVO() {
		this(0,"","","","","");
	}

	public PostVO(int postId, String title, String nickname,String postDate) {
		this(postId, title, nickname, "","",postDate);
	}

	public PostVO(int postId, String title, String nickname, String filename, String content, String postDate) {
		setPostId(postId);
		setTitle(title);
		setNickname(nickname);
		setFileName(filename);
		setContent(content);
		setPostDate(postDate);
	}
	

	public String getGood() {
		return good;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public String getClick() {
		return click;
	}

	public void setClick(String click) {
		this.click = click;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostDate() {
		return postDate;
	}
	
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		return "PostVO [postId=" + postId + ", title=" + title + ", nickname=" + nickname + ", fileName=" + fileName
				+ ", content=" + content + ", postDate=" + postDate + ", ticketNumber=" + ticketNumber + ", email="
				+ email + "]";
	}



}
