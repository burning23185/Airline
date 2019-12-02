package com.ws.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.ws.dao.CommentDAO;
import com.ws.dao.PostDAO;
import com.ws.dao.impl.CommentDAOImpl;


public class GetCommentAction implements Action {
	private CommentDAOImpl dao;

	public GetCommentAction(CommentDAO cdao) {
		this.dao = (CommentDAOImpl) cdao;
	}

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		request.setAttribute("comment", new Gson().toJson(dao.getCommentByPostId(Integer.parseInt(request.getParameter("postId")))));
		return "getComment.jsp";
	}

}
