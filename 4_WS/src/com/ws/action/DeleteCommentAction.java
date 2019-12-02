package com.ws.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.ws.dao.CommentDAO;
import com.ws.dao.PostDAO;
import com.ws.dao.impl.CommentDAOImpl;
import com.ws.dao.impl.PostDAOImpl;
import com.ws.vo.CommentVO;

public class DeleteCommentAction implements Action {
	private CommentDAOImpl cdao;

	public DeleteCommentAction(CommentDAO cdao) {
		this.cdao=(CommentDAOImpl) cdao;
	}
	
	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		CommentVO vo=null;
		vo= new CommentVO(Integer.parseInt(request.getParameter("commentNumber")), Integer.parseInt(request.getParameter("postId")));
		cdao.deleteComment(vo);
		return "postDetail.html";
	}
}
