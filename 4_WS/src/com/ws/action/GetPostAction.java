package com.ws.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.ws.dao.PostDAO;
import com.ws.dao.impl.PostDAOImpl;

public class GetPostAction implements Action {
	private PostDAOImpl dao;

	public GetPostAction(PostDAO pdao) {
		this.dao = (PostDAOImpl) pdao;
	}

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		int postId = Integer.parseInt(request.getParameter("postId"));
		String nickname = null;
		if (request.getParameter("nickname") != "")
			nickname = request.getParameter("nickname");
		if (nickname != null && dao.isClick(postId, nickname) == 0) {
			dao.updateClick(postId);
			dao.addClick(postId, nickname);
		}
		request.setAttribute("post", new Gson().toJson(dao.getPost(postId)));
		return "getPost.jsp";
	}
}
