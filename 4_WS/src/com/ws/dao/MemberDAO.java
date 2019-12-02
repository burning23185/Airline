package com.ws.dao;

import com.ws.vo.MemberVO;

public interface MemberDAO {

	String login(MemberVO vo);

	boolean isEmail(String email);

	boolean isNickName(String nickname);

	MemberVO getMember(MemberVO vo);

	boolean addMember(MemberVO vo);

}
