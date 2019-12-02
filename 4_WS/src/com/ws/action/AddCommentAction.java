package com.ws.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.ws.dao.CommentDAO;
import com.ws.dao.PostDAO;
import com.ws.dao.impl.CommentDAOImpl;
import com.ws.vo.CommentVO;

public class AddCommentAction implements Action {
	private CommentDAOImpl dao;

	public AddCommentAction(CommentDAO cdao) {
		this.dao = (CommentDAOImpl) cdao;
	}

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		
		CommentVO cvo = new CommentVO(0,Integer.parseInt(request.getParameter("postId")));
		cvo.setCommentContent(request.getParameter("commentContent"));
		cvo.setNickname(request.getParameter("nickname"));
		cvo.setEmail(request.getParameter("email"));
		dao.addComment(cvo);
		
		return "postDetail.html";
	}

}