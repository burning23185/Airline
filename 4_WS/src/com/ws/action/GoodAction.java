package com.ws.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.ws.dao.PostDAO;
import com.ws.dao.impl.PostDAOImpl;

public class GoodAction implements Action {
	private PostDAOImpl dao;

	public GoodAction(PostDAO dao) {
		this.dao = (PostDAOImpl) dao;
	}

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		int postId = Integer.parseInt(request.getParameter("postId"));
		String nickname = null;
		if (request.getParameter("nickname") != "")
			nickname = request.getParameter("nickname");

		if (request.getParameter("good") != null && nickname != null) {
			switch (request.getParameter("good")) {
			case "up":
				if (dao.isGood(postId, nickname)) {
					dao.upGood(postId);
					dao.clickGood(postId, nickname);
				}
				break;

			case "down":
				if (dao.isNoGood(postId, nickname))
					dao.downGood(postId);
				dao.clickNoGood(postId, nickname);
				break;
			default:
				break;
			}
		}
		request.setAttribute("good", dao.selectGood(postId));

		return "getGood.jsp";
	}

}