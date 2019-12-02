package com.ws.vo;

public class CommentVO {
	private int commentNumber;
	private int postId;
	private String commentTime;
	private String commentContent;
	private String nickname;
	private String email;
	
	public CommentVO() {
		this(0,0,"","","","");
	}

	public CommentVO(int commentNumber, int postId) {
		this(commentNumber,postId,"","","","");
	}
	
	public CommentVO(int commentNumber, String commentTime, String commentContent, String nickname) {
		this(commentNumber,0,commentTime,commentContent,nickname,"");
	}
	
	public CommentVO(int commentNumber, int postId, String commentTime, String commentContent, String nickname,
			String email) {
		this.commentNumber = commentNumber;
		this.postId = postId;
		this.commentTime = commentTime;
		setCommentContent(commentContent);
		this.nickname = nickname;
		setEmail(email);
	}

	

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCommentNumber() {
		return commentNumber;
	}

	public int getPostId() {
		return postId;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentNumber;
		result = prime * result + ((commentTime == null) ? 0 : commentTime.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + postId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentVO other = (CommentVO) obj;
		if (commentNumber != other.commentNumber)
			return false;
		if (commentTime == null) {
			if (other.commentTime != null)
				return false;
		} else if (!commentTime.equals(other.commentTime))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (postId != other.postId)
			return false;
		return true;
	}
	
	
	
	
}
