package com.spring.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.spring.dto.BoardDTO;

@Repository //현재 클래스를 dao bean으로 등록
public class BoardDAOImpl implements BoardDAO {
	@Inject
	SqlSessionTemplate sqlSessionTemplate; //SqlSessionTemplate 의존관계 주입

	@Override
	public List<BoardDTO> list(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectList("board.list", param);
	}

	@Override
	public int create(BoardDTO dto) {
		return sqlSessionTemplate.insert("board.create", dto);
	}

	@Override
	public BoardDTO detail(int code) {
		return sqlSessionTemplate.selectOne("board.detail", code);
	}

	@Override
	public void view(int code) {
		sqlSessionTemplate.update("board.view", code);
	}

	@Override
	public int update(BoardDTO dto) {
		return sqlSessionTemplate.update("board.update", dto);
	}

	@Override
	public int delete(int code) {
		return sqlSessionTemplate.update("board.delete", code);
	}

	@Override
	public int count(HashMap<String, Object> param) {
		return sqlSessionTemplate.selectOne("board.count", param);
	}
}
