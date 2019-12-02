package com.ws.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.ws.dao.PostDAO;
import com.ws.dao.impl.PostDAOImpl;
import com.ws.vo.PostVO;

public class RegistPostAction implements Action {
	private PostDAOImpl dao;

	public RegistPostAction(PostDAO pdao) {
		this.dao = (PostDAOImpl) pdao;
	}

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		
		String path = "C:\\OOPSW\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\4_WS\\upload";
		String encType = "UTF-8";
		int sizeLimit = 100 * 1024 * 1024;
		MultipartRequest multi;
		PostVO pvo = null;
		
		try {
			multi = new MultipartRequest(request, path, sizeLimit, encType,
					new DefaultFileRenamePolicy());
			pvo =  new PostVO(0,  multi.getParameter("title"), multi.getParameter("nickname"), "");
			pvo.setContent(multi.getParameter("content"));
			if(multi.getFilesystemName("fileName")!= null)
			pvo.setFileName(multi.getFilesystemName("fileName"));
			pvo.setTicketNumber( multi.getParameter("ticketNum"));
			pvo.setEmail(multi.getParameter("email"));
			dao.addPost(pvo);

		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return "postBoard.html";
	}
}
