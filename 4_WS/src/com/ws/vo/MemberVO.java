package com.ws.vo;

public class MemberVO {
	private String email;
	private String pw;
	private String nickname;
	private String name;
	private String inDate;

	public MemberVO() {
		this("", "", "", "", "");
	}
	
	public MemberVO(String email, String pw) {
		this(email,pw,"","","");
	}
	
	public MemberVO(String email, String pw, String nickname) {
		this(email,pw,nickname,"","");
	}

	public MemberVO(String email, String pw, String nickname, String name, String inDate) {
		setEmail(email);
		setPw(pw);
		setNickname(nickname);
		setName(name);
		setInDate(inDate);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	@Override
	public String toString() {
		return "email=" + email + " pw=" + pw + " nickname=" + nickname + " name=" + name + " inDate=" + inDate + "\n";
	}

}
