package com.ws.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.ws.dao.MemberDAO;
import com.ws.dao.PostDAO;
import com.ws.dao.impl.MemberDAOImpl;
import com.ws.dao.impl.PostDAOImpl;

public class CheckOverlapAction implements Action {
	private MemberDAOImpl dao;
	private PostDAOImpl pdao;

	public CheckOverlapAction(MemberDAO dao,PostDAO pdao) {
		this.dao = (MemberDAOImpl) dao;
		this.pdao = (PostDAOImpl)  pdao;
	}

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		boolean result = false;
		if (request.getParameter("email") != null) {
			result = dao.isEmail(request.getParameter("email"));
		} else if (request.getParameter("nickname") != null) {
			result = dao.isNickName(request.getParameter("nickname"));
		} else if (request.getParameter("ticketNumber") != null) {
			result = pdao.isTicketNumber(request.getParameter("ticketNumber"));
		}
		
		request.setAttribute("result", new Gson().toJson(result));
		
		return "overlapCheck.jsp";
	}
}
