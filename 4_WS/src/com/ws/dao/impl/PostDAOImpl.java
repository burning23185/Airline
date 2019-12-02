package com.ws.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.ws.dao.PostDAO;
import com.ws.vo.PostVO;

public class PostDAOImpl implements PostDAO {
	private SqlSessionFactory sqlSessionFactory;

	public PostDAOImpl() throws ClassNotFoundException, SQLException {
		InputStream inputStream = null;
		try {
			String resource = "config/mybatis-Config.xml";
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	// 게시판 리스트 불러오기
	@Override
	public ArrayList<PostVO> getList(int startNo, int endNo) {
		SqlSession session = sqlSessionFactory.openSession();
		Collection<PostVO> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		try {
			list = session.selectList("postMapper.selectPostList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (ArrayList<PostVO>) list;
	}

	// 검색된 게시판 리스트 불러오기
	@Override
	public ArrayList<PostVO> searchListByText(int startNo, int endNo, String searchText) {
		SqlSession session = sqlSessionFactory.openSession();

		Collection<PostVO> list = new ArrayList<>();
		HashMap<String, Object> hashmap = new HashMap<>();
		hashmap.put("startNo", startNo);
		hashmap.put("endNo", endNo);
		hashmap.put("searchText", searchText);

		try {
			list = session.selectList("postMapper.searchTitle", hashmap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (ArrayList<PostVO>) list;
	}

	// 닉네임 검색
	@Override
	public ArrayList<PostVO> searchListByNickname(int startNo, int endNo, String searchText) {
		SqlSession session = sqlSessionFactory.openSession();

		Collection<PostVO> list = new ArrayList<>();
		HashMap<String, Object> hashmap = new HashMap<>();
		hashmap.put("startNo", startNo);
		hashmap.put("endNo", endNo);
		hashmap.put("searchText", searchText);

		try {
			list = session.selectList("postMapper.searchNickname", hashmap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (ArrayList<PostVO>) list;
	}

	// 게시글 불러오기
	@Override
	public PostVO getPost(int postId) {
		SqlSession session = sqlSessionFactory.openSession();
		PostVO vo = null;
		try {
			vo = session.selectOne("postMapper.selectPost", postId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return vo;
	}

	// 게시글 작성
	@Override
	public boolean addPost(PostVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		try {
			session.insert("postMapper.addPost", vo);
			session.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 게시글 수정
	@Override
	public boolean updatePost(PostVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;

		try {
			session.update("postMapper.updatePost", vo);
			session.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public boolean updatePostNoFile(PostVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;

		try {
			session.update("postMapper.updatePostNoFile", vo);
			session.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 게시글 전체 갯수
	@Override
	public int getMaxCount() {
		SqlSession session = sqlSessionFactory.openSession();
		int num = -1;
		try {
			num = session.selectOne("postMapper.selectMaxNum");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return num;
	}

	// 검색된 글 갯수
	@Override
	public int getMaxCountText(String searchText) {

		SqlSession session = sqlSessionFactory.openSession();
		int num = -1;
		try {
			num = session.selectOne("postMapper.selectMaxText", searchText);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return num;
	}

	// 닉네임 검색 개수
	@Override
	public int getMaxCountNickname(String searchText) {
		SqlSession session = sqlSessionFactory.openSession();
		int num = -1;
		try {
			num = session.selectOne("postMapper.selectMaxNumNick", searchText);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return num;
	}

	// 티켓 중복검사
	@Override
	public boolean isTicketNumber(String number) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		try {
			result = ((Integer) session.selectOne("postMapper.isTickNum", number) >= 1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 게시글 삭제
	@Override
	public boolean deletePost(int postId) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		try {
			session.update("postMapper.updatePost", postId);
			session.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public boolean updateClick(int postId) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;

		try {
			session.update("postMapper.updateClick", postId);
			session.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public boolean addClick(int postId, String nickname) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		HashMap<String, Object> hashmap = new HashMap<>();
		hashmap.put("postId", postId);
		hashmap.put("nickname", nickname);
		try {
			session.insert("postMapper.addClick", hashmap);
			session.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public boolean upGood(int postId) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		try {
			session.update("postMapper.updateGoodUp", postId);
			session.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public boolean downGood(int postId) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		try {
			session.update("postMapper.updateGoodDown", postId);
			session.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int selectGood(int postId) {
		SqlSession session = sqlSessionFactory.openSession();
		int num = -1;
		try {
			num = session.selectOne("postMapper.selectGood", postId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return num;
	}

	@Override
	public boolean clickGood(int postId, String nickname) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		HashMap<String, Object> hashmap = new HashMap<>();
		hashmap.put("postId", postId);
		hashmap.put("nickname", nickname);
		try {
			session.update("postMapper.updateGoodClick", hashmap);
			session.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public boolean clickNoGood(int postId, String nickname) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		HashMap<String, Object> hashmap = new HashMap<>();
		hashmap.put("postId", postId);
		hashmap.put("nickname", nickname);
		try {
			session.update("postMapper.updateNoGoodClick", hashmap);
			session.commit();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int isClick(int postId, String nickname) {
		SqlSession session = sqlSessionFactory.openSession();
		int result = -1;
		HashMap<String, Object> hashmap = new HashMap<>();
		hashmap.put("postId", postId);
		hashmap.put("nickname", nickname);
		try {
			result = (int) session.selectOne("postMapper.isClick", hashmap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public boolean isGood(int postId, String nickname) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		HashMap<String, Object> hashmap = new HashMap<>();
		hashmap.put("postId", postId);
		hashmap.put("nickname", nickname);
		try {
			result = ((int) session.selectOne("postMapper.isGood", hashmap) == 0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public boolean isNoGood(int postId, String nickname) {
		SqlSession session = sqlSessionFactory.openSession();
		boolean result = false;
		HashMap<String, Object> hashmap = new HashMap<>();
		hashmap.put("postId", postId);
		hashmap.put("nickname", nickname);
		try {
			result = ((int) session.selectOne("postMapper.isNoGood", hashmap) == 0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public ArrayList<PostVO> getclickList(int startNo, int endNo) {
		SqlSession session = sqlSessionFactory.openSession();
		Collection<PostVO> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		try {
			list = session.selectList("postMapper.selectClickPost", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (ArrayList<PostVO>) list;
	}

	@Override
	public ArrayList<PostVO> getGoodList(int startNo, int endNo) {
		SqlSession session = sqlSessionFactory.openSession();
		Collection<PostVO> list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		try {
			list = session.selectList("postMapper.selectGoodPost", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (ArrayList<PostVO>) list;
	}
}
