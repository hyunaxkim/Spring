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

@Controller //��Ʈ�ѷ� ������ ���
@RequestMapping("/board/*") //Ŭ���� �� �޼ҵ忡 �ϰ�����
public class BoardController {
	
	@Inject
	BoardService boardService;
	
	//�Խ��� ����Ʈ ȭ��
	@RequestMapping(value = "/list", method = RequestMethod.GET) // /board/list
	public ModelAndView getList(
		@RequestParam(value = "page", required = false, defaultValue = "1") int page,
		@RequestParam(value = "searchType", required = false, defaultValue = "") String searchType,
		@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword
	) {
		//�ؽø��� �̿��Ͽ� �ϳ��� �׷������� �� ������ ����
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
		mav.addObject("list", list); //�信 ������ ����
		mav.addObject("paging", paging);
		mav.addObject("currentPageNum", page);
		mav.addObject("searchType", searchType);
		mav.addObject("keyword", keyword);
		
		return mav;
	}
	
	//�Խ��� �ۼ� ȭ��
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "board/create";
	}
	
	//�Խ��� �ۼ� post
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody //http ��û body�� �ڹ� ��ü�� ���� �� �ֵ��� �ϴ� ������̼�
	public HashMap<String, String> create(@ModelAttribute BoardDTO dto) {
		int result = boardService.create(dto); //���� ���� ���. ������ 1, ���н� 0
		
		HashMap<String, String> msg = new HashMap<String, String>();
		if(result>=1) { //�۾��� ����
			msg.put("status", "success");
		}else { //�۾��� ����
			msg.put("status", "fail");
		}
		return msg;
	}
	
	//�Խ��� �� ȭ��
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam("no") int code) {
		boardService.view(code); //��ȸ�� count ����
		BoardDTO detail = boardService.detail(code); //�Խ��� ������ ��������
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/detail");
		mav.addObject("detail", detail);
		return mav;
	}
	
	//�Խ��� ���� ȭ��
	@RequestMapping(value = "/update/{no}")
	public ModelAndView update(@PathVariable int no) {
		BoardDTO detail = boardService.detail(no); //�Խ��� ������ ��������
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/update");
		mav.addObject("detail", detail);
		return mav;
	}
	
	//�Խ��� ���� post
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody //http ��û body�� �ڹ� ��ü�� ���� �� �ֵ��� �ϴ� ������̼�
	public HashMap<String, String> update(@ModelAttribute BoardDTO dto) {
		int result = boardService.update(dto); //���� ���� ���. ������ 1�̻�, ���н� 0
		
		HashMap<String, String> msg = new HashMap<String, String>();
		if(result>=1) { //���� ����
			msg.put("status", "success");
		}else { //���� ����
			msg.put("status", "fail");
		}
		return msg;
	}
	
	//�Խ��� ���� post
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody //http ��û body�� �ڹ� ��ü�� ���� �� �ֵ��� �ϴ� ������̼�
	public HashMap<String, String> delete(@RequestBody HashMap<String, String> data) {
		int code = Integer.parseInt(String.valueOf(data.get("code"))); //int�� ����ȯ
		int result = boardService.delete(code); //���� ���� ���. ������ 1�̻�, ���н� 0
		
		HashMap<String, String> msg = new HashMap<String, String>();
		if(result>=1) { //���� ����
			msg.put("status", "success");
		}else { //���� ����
			msg.put("status", "fail");
		}
		return msg;
	}
	
}
