package com.ws.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ws.dao.CommentDAO;
import com.ws.vo.CommentVO;


public class CommentDAOImpl implements CommentDAO {

	private SqlSessionFactory sqlSessionFactory;
	
	public CommentDAOImpl() throws ClassNotFoundException, SQLException {

			InputStream inputStream = null;
			try {
				String resource = "config/mybatis-Config.xml";
				inputStream = Resources.getResourceAsStream(resource);
			} catch (IOException e) {
				e.printStackTrace();
			}
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); 
		}

	@Override
	public boolean addComment(CommentVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		try {
			session.insert("commentMapper.addComment", vo);
			session.commit();
			result = true;
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result; 
	}

	@Override
	public ArrayList<CommentVO> getCommentByPostId(int postId) {
		SqlSession session = sqlSessionFactory.openSession();
		Collection<CommentVO> list=null;
		try {
			list = session.selectList("commentMapper.selectCommentList", postId);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (ArrayList<CommentVO>) list;
	}

	// 3. µ¡±Û ¼öÁ¤
	@Override
	public boolean deleteComment(CommentVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		
		try {
			session.update("commentMapper.updateComment", vo);
			session.commit();
			result = true;
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result; 

	}
}
