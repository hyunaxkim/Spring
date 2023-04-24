package com.spring.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.BoardDTO;
import com.spring.dto.PageDTO;
import com.spring.service.BoardService;

@Controller //컨트롤러 빈으로 등록
@RequestMapping("/board/*") //클래스 내 메소드에 일괄적용
public class BoardController {
	
	@Inject
	BoardService boardService;
	
	//게시판 리스트 화면
	@RequestMapping(value = "/list", method = RequestMethod.GET) // /board/list
	public ModelAndView getList(
		@RequestParam(value = "page", required = false, defaultValue = "1") int page,
		@RequestParam(value = "searchType", required = false, defaultValue = "") String searchType,
		@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword
	) {
		//해시맵을 이용하여 하나로 그룹지어준 뒤 데이터 전달
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("searchType", searchType);
		param.put("keyword", keyword);
				
		PageDTO paging = new PageDTO();
		paging.setPage(page);
		paging.setCount(boardService.count(param));
	
		param.put("start", paging.getStart());
		param.put("limit", paging.getLimit());
		List<BoardDTO> list = boardService.list(param);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list");
		mav.addObject("list", list); //뷰에 데이터 전달
		mav.addObject("paging", paging);
		mav.addObject("currentPageNum", page);
		mav.addObject("searchType", searchType);
		mav.addObject("keyword", keyword);
		
		return mav;
	}
	
	//게시판 작성 화면
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "board/create";
	}
	
	//게시판 작성 post
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody //http 요청 body를 자바 객체로 받을 수 있도록 하는 어노테이션
	public HashMap<String, String> create(@ModelAttribute BoardDTO dto) {
		int result = boardService.create(dto); //영향 받은 행수. 성공시 1, 실패시 0
		
		HashMap<String, String> msg = new HashMap<String, String>();
		if(result>=1) { //글쓰기 성공
			msg.put("status", "success");
		}else { //글쓰기 실패
			msg.put("status", "fail");
		}
		return msg;
	}
	
	//게시판 상세 화면
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam("no") int code) {
		boardService.view(code); //조회수 count 증가
		BoardDTO detail = boardService.detail(code); //게시판 상세정보 가져오기
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/detail");
		mav.addObject("detail", detail);
		return mav;
	}
	
	//게시판 수정 화면
	@RequestMapping(value = "/update/{no}")
	public ModelAndView update(@PathVariable int no) {
		BoardDTO detail = boardService.detail(no); //게시판 상세정보 가져오기
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/update");
		mav.addObject("detail", detail);
		return mav;
	}
	
	//게시판 수정 post
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody //http 요청 body를 자바 객체로 받을 수 있도록 하는 어노테이션
	public HashMap<String, String> update(@ModelAttribute BoardDTO dto) {
		int result = boardService.update(dto); //영향 받은 행수. 성공시 1이상, 실패시 0
		
		HashMap<String, String> msg = new HashMap<String, String>();
		if(result>=1) { //수정 성공
			msg.put("status", "success");
		}else { //수정 실패
			msg.put("status", "fail");
		}
		return msg;
	}
	
	//게시판 삭제 post
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody //http 요청 body를 자바 객체로 받을 수 있도록 하는 어노테이션
	public HashMap<String, String> delete(@RequestBody HashMap<String, String> data) {
		int code = Integer.parseInt(String.valueOf(data.get("code"))); //int로 형변환
		int result = boardService.delete(code); //영향 받은 행수. 성공시 1이상, 실패시 0
		
		HashMap<String, String> msg = new HashMap<String, String>();
		if(result>=1) { //삭제 성공
			msg.put("status", "success");
		}else { //삭제 실패
			msg.put("status", "fail");
		}
		return msg;
	}
	
}
