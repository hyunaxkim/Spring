
package com.spring.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.dao.BoardDAO;
import com.spring.dto.BoardDTO;

@Service //service bean으로 등록
public class BoardServiceImpl implements BoardService {
	@Inject
	BoardDAO boardDao;

	@Override
	public List<BoardDTO> list(HashMap<String, Object> param) {
		return boardDao.list(param);
	}

	@Override
	public int create(BoardDTO dto) {
		return boardDao.create(dto);
	}

	@Override
	public BoardDTO detail(int code) {
		return boardDao.detail(code);
	}

	@Override
	public void view(int code) {
		boardDao.view(code);
	}

	@Override
	public int update(BoardDTO dto) {
		return boardDao.update(dto);
	}

	@Override
	public int delete(int code) {
		return boardDao.delete(code);
	}

	@Override
	public int count(HashMap<String, Object> param) {
		return boardDao.count(param);
	}
	
}
