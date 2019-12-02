package com.ws.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import com.ws.dao.MemberDAO;
import com.ws.vo.MemberVO;

import util.SHA256;

public class SignupAction implements Action {
	private MemberDAO dao;

	public SignupAction(MemberDAO dao) {
		this.dao = dao;
	}

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String page = "postBoard.html";
		MemberVO mvo = new MemberVO(request.getParameter("newEmail"), new SHA256().encryptSHA256(request.getParameter("pw")) , request.getParameter("nickName"));
		mvo.setName(request.getParameter("name"));
		System.out.println(new SHA256().encryptSHA256(request.getParameter("pw")));
		if (dao.addMember(mvo))
			page = "";
		return page;
	}
}
