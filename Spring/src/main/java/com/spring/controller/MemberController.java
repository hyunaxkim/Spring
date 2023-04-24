package com.spring.controller;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.dto.MemberDTO;
import com.spring.service.MemberService;

@Controller //��Ʈ�ѷ� ������ ���
@RequestMapping("/member/*") //Ŭ���� �� �޼ҵ忡 �ϰ�����
public class MemberController {
	
	@Inject
	MemberService memberService;
	
	//�α��� ȭ��
	@RequestMapping(value = "/login", method = RequestMethod.GET) // /member/login
	public String login() {
		return "member/login";
	}
	
	//�α��� post
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute MemberDTO dto, HttpSession session) {
		int code = memberService.login(dto, session);
		ModelAndView mav = new ModelAndView();
		if(code != 0) { //�α��� ����
			mav.setViewName("redirect:/main"); //���� ȭ������ redirect. ��ΰ� �ƿ� �ٲ�
		}else { //�α��� ����
			mav.setViewName("member/login"); //������ ��ο� ȭ�鸸 �α��� view ȭ���� ������
			mav.addObject("status", "error"); //�信 ������ ����
		}
		return mav;
	}
	
	//�α׾ƿ� post
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpSession session, RedirectAttributes ra) {
		memberService.logout(session); //�α׾ƿ�
		ra.addFlashAttribute("status", "logout"); //redirect �� ������ ����. ��ȸ������ ���ΰ�ħ�ϸ� �� �����.
		return "redirect:/main"; //���� ȭ������ redirect. ��ΰ� �ƿ� �ٲ�
	}
	
	//ȸ������ ȭ��
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "member/join";
	}
	
	//���̵� �ߺ� üũ post
	@RequestMapping(value = "/join/idcheck", method = RequestMethod.POST)
	@ResponseBody //http ��û body�� �ڹ� ��ü�� ���� �� �ֵ��� �ϴ� ������̼�
	public HashMap<String, String> idcheck(@ModelAttribute MemberDTO dto) {
		String id = memberService.idcheck(dto); //���̵� �ߺ�üũ
		
		HashMap<String, String> msg = new HashMap<String, String>();
		if(id != null) { //���̵� ����. ���Ұ����� ���̵�
			msg.put("status", "fail");
		}else { //��밡���� ���̵�
			msg.put("status", "success");
			msg.put("id", dto.getId());
		}
		
		return msg;
	}
	
	//ȸ������ post
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@ResponseBody //http ��û body�� �ڹ� ��ü�� ���� �� �ֵ��� �ϴ� ������̼�
	public HashMap<String, String> join(@ModelAttribute MemberDTO dto) {
		int result = memberService.join(dto); //���� ���� ���. ������ 1, ���н� 0
		
		HashMap<String, String> msg = new HashMap<String, String>();
		if(result>=1) { //ȸ������ ����
			msg.put("status", "success");
		}else { //ȸ������ ����
			msg.put("status", "fail");
		}
		return msg;
	}
}
