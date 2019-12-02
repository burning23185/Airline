package com.ws.dao;

import java.util.ArrayList;

import com.ws.vo.CommentVO;

public interface CommentDAO {

	boolean addComment(CommentVO vo);

	ArrayList<CommentVO> getCommentByPostId(int postId);

	boolean deleteComment(CommentVO vo);
}
