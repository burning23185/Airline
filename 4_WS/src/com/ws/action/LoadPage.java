package com.ws.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class LoadPage implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String page = null;
		String url = "";
		if (request.getParameter("url") != null)
			url = request.getParameter("url");
		switch (url) {
		case "main":
			page = "main.html";
			break;
		case "regist":
			page = "postWriting.html";
			break;
		case "edit":
			page = "postEdit.html";
			break;
		case "post":
			page = "postDetail.html";
			break;
		case "board":
			page = "postBoard.html";
			break;
		case "comment":
			page = "postDetail.html";
			break;
		case "myPage":
			page = "myPage.html";
			break;
		case "introduce":
			page = "introduce.html";
			break;
		case "signup":
			page = "signup.html";
			break;
		case "event":
			page = "event.html";
			break;
		case "news":
			page = "news.html";
			break;
		default:
			page = "main.html";
		}

		return page;
	}
}
