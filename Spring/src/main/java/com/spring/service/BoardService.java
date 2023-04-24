package com.spring.service;

import java.util.HashMap;
import java.util.List;

import com.spring.dto.BoardDTO;

public interface BoardService {
	public List<BoardDTO> list(HashMap<String, Object> param);
	public int create(BoardDTO dto);
	public BoardDTO detail(int code);
	public void view(int code);
	public int update(BoardDTO dto);
	public int delete(int code);
	public int count(HashMap<String, Object> param);
}
