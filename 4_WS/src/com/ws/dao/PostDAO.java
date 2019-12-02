package com.ws.dao;

import java.util.ArrayList;
import com.ws.vo.PostVO;

public interface PostDAO {
	ArrayList<PostVO> getList(int startNo, int endNo);

	ArrayList<PostVO> searchListByText(int startNo, int endNo, String searchText);

	ArrayList<PostVO> searchListByNickname(int startNo, int endNo, String searchText);

	PostVO getPost(int postId);

	boolean addPost(PostVO vo);

	boolean updatePost(PostVO vo);
	
	boolean updatePostNoFile(PostVO vo);
	
	int getMaxCount();

	int getMaxCountNickname(String searchText);

	int getMaxCountText(String searchText);
	
	boolean isTicketNumber(String number);
	
	boolean deletePost(int postId);
	
	boolean updateClick(int postId);
	
	boolean upGood(int postId);
	
	boolean downGood(int postId);
	
	boolean clickGood(int postId, String nickname);
	
	boolean clickNoGood(int postId, String nickname);
	
	boolean addClick(int postId, String nickname);
	
	int selectGood(int postId);
	
	int isClick(int postId, String nickname);
	
	boolean isGood(int postId, String nickname);
	
	boolean isNoGood(int postId, String nickname);
	
	ArrayList<PostVO> getclickList(int startNo, int endNo);
	
	ArrayList<PostVO> getGoodList(int startNo, int endNo);
	

}
