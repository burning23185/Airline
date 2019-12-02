package com.ws.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ws.action.Action;
import com.ws.dao.CommentDAO;
import com.ws.dao.MemberDAO;
import com.ws.dao.PostDAO;
import com.ws.dao.impl.CommentDAOImpl;
import com.ws.dao.impl.MemberDAOImpl;
import com.ws.dao.impl.PostDAOImpl;
import com.ws.factory.CmdFactory;

/**
 * Servlet implementation class FrontControllerServlet
 */
@WebServlet("/frontController")
public class FrontControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MemberDAO dao = null;
	private PostDAO pdao = null;
	private CommentDAO cdao = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			dao = new MemberDAOImpl();
			pdao = new PostDAOImpl();
			cdao = new CommentDAOImpl();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		CmdFactory factory = new CmdFactory(dao,pdao,cdao); 
		String cmd = request.getParameter("cmd");
		if (cmd == null || cmd.trim().length() < 5)
			cmd = "index";
		String page = "";
		Action a = factory.cmdActionFactory(cmd);
		page = a.execute(request);
		request.getRequestDispatcher("/" + page).forward(request, response);//
	}// service
}
