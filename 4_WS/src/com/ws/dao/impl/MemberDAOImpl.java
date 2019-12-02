package com.ws.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ws.dao.MemberDAO;
import com.ws.vo.MemberVO;
import com.ws.vo.PostVO;

import util.SetDriver;

public class MemberDAOImpl implements MemberDAO {
	private SqlSessionFactory sqlSessionFactory;

	public MemberDAOImpl( ) throws ClassNotFoundException, SQLException {
			InputStream inputStream = null;
			try {
				String resource = "config/mybatis-Config.xml";
				inputStream = Resources.getResourceAsStream(resource);
			} catch (IOException e) {
				e.printStackTrace();
			}
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); 
		}


	// 로그인
	@Override
	public String login(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		String result = null;
		try {
			result = session.selectOne("memberMapper.login", vo);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 아이디 중복 여부
	@Override
	public synchronized boolean isEmail(String email) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		try {
			result = ((Integer)session.selectOne("memberMapper.isEmail",email)==1);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
	//닉네임 중복 여부
	@Override
	public boolean isNickName(String nickname) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		try {
			result = ((Integer)session.selectOne("memberMapper.isNickname",nickname)==1);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 특정 회원 정보 확인 - id / select
	@Override
	public MemberVO getMember(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		MemberVO result = null;
		try {
			result = session.selectOne("memberMapper.selectMember", vo);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 회원가입
	@Override
	public boolean addMember(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		try {
			session.insert("memberMapper.addMember", vo);
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

