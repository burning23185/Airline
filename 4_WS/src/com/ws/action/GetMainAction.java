package com.ws.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.ws.dao.PostDAO;
import com.ws.dao.impl.PostDAOImpl;

public class GetMainAction implements Action {
	private PostDAOImpl dao;

	public GetMainAction(PostDAO pdao) {
		this.dao = (PostDAOImpl) pdao;
	}

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		int startNo = 1;
		int endNo = 5;
		request.setAttribute("goodList", new Gson().toJson(dao.getGoodList(startNo, endNo)));
		request.setAttribute("clickList", new Gson().toJson(dao.getclickList(startNo, endNo)));
		return "getMain.jsp";
	}
}
