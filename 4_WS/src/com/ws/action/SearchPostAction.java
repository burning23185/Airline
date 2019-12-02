package com.ws.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import com.google.gson.Gson;
import com.ws.dao.PostDAO;
import com.ws.dao.impl.PostDAOImpl;
import com.ws.vo.PostVO;
import util.PageUtil;

public class SearchPostAction implements Action {
	private PostDAOImpl dao;

	public SearchPostAction(PostDAO pdao) {
		this.dao = (PostDAOImpl) pdao;
	}

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
	
		int boardSize = 0;
		int setListHight = 10; // 한 페이지에 보여줄 갯수
		int nowPage = 1; // 현재 페이지 넘버
		
		List<PostVO> list = new ArrayList<>();
		if (request.getParameter("pageNumber") != null && !request.getParameter("pageNumber").equals("")) {
			nowPage = Integer.parseInt(request.getParameter("pageNumber"));
		}
		
		int startNum = ((nowPage - 1) * setListHight + 1);
		int endNum = (nowPage * setListHight);
		
		if (request.getParameter("searchText") != null && !request.getParameter("searchText").equals("")) {
			String text = request.getParameter("searchText");
			switch (request.getParameter("searchType")) {
			case "제목내용":
				list = dao.searchListByText(startNum, endNum, text);
				boardSize = dao.getMaxCountText(text);
				break;
			case "작성자":
				list = dao.searchListByNickname(startNum, endNum, text);
				boardSize = dao.getMaxCountNickname(text);
				break;
			}
		} else {
			list = dao.getList(startNum, endNum);
			boardSize = dao.getMaxCount();
		}
		
		PageUtil p_Util = new PageUtil(setListHight, setListHight, boardSize);

		request.setAttribute("list", new Gson().toJson(list));
		request.setAttribute("nextBlock", p_Util.getNextBlock(nowPage));
		request.setAttribute("prevBlock", p_Util.getPrevBlock(nowPage));
		request.setAttribute("totalPageNum",p_Util.getTotalNumOfPage(boardSize));
		request.setAttribute("blockList", new Gson().toJson(p_Util.getBlockList(nowPage)));
		
		return "getBoard.jsp";
	}
}
