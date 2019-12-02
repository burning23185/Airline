package com.ws.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.ws.dao.PostDAO;
import com.ws.dao.impl.PostDAOImpl;
import com.ws.vo.PostVO;


public class EditPostAction implements Action {
	private PostDAOImpl dao;

	public EditPostAction(PostDAO pdao) {
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
			multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
			pvo = new PostVO();
			pvo.setPostId(Integer.parseInt(multi.getParameter("postId")));
			pvo.setContent(multi.getParameter("content"));
			
			if(multi.getFilesystemName("fileName")!= null){	
				pvo.setFileName(multi.getFilesystemName("fileName"));
				dao.updatePost(pvo);
			}else{
				dao.updatePostNoFile(pvo);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return "postDetail.html";
	}
}
