package com.ws.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import com.ws.dao.PostDAO;
import com.ws.dao.impl.PostDAOImpl;
import com.ws.vo.PostVO;

public class DeletePostAction implements Action {
	private PostDAOImpl dao;
	public DeletePostAction(PostDAO pdao) {
		this.dao = (PostDAOImpl) pdao;
	}
	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		dao.deletePost(Integer.parseInt(request.getParameter("postId")));
		return "postBoard.html";
	}
}