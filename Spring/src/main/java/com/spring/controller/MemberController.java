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

@Controller //컨트롤러 빈으로 등록
@RequestMapping("/member/*") //클래스 내 메소드에 일괄적용
public class MemberController {
	
	@Inject
	MemberService memberService;
	
	//로그인 화면
	@RequestMapping(value = "/login", method = RequestMethod.GET) // /member/login
	public String login() {
		return "member/login";
	}
	
	//로그인 post
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute MemberDTO dto, HttpSession session) {
		int code = memberService.login(dto, session);
		ModelAndView mav = new ModelAndView();
		if(code != 0) { //로그인 성공
			mav.setViewName("redirect:/main"); //메인 화면으로 redirect. 경로가 아예 바뀜
		}else { //로그인 실패
			mav.setViewName("member/login"); //동일한 경로에 화면만 로그인 view 화면을 보여줌
			mav.addObject("status", "error"); //뷰에 데이터 전달
		}
		return mav;
	}
	
	//로그아웃 post
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpSession session, RedirectAttributes ra) {
		memberService.logout(session); //로그아웃
		ra.addFlashAttribute("status", "logout"); //redirect 시 데이터 전달. 일회성으로 새로고침하면 값 사라짐.
		return "redirect:/main"; //메인 화면으로 redirect. 경로가 아예 바뀜
	}
	
	//회원가입 화면
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "member/join";
	}
	
	//아이디 중복 체크 post
	@RequestMapping(value = "/join/idcheck", method = RequestMethod.POST)
	@ResponseBody //http 요청 body를 자바 객체로 받을 수 있도록 하는 어노테이션
	public HashMap<String, String> idcheck(@ModelAttribute MemberDTO dto) {
		String id = memberService.idcheck(dto); //아이디 중복체크
		
		HashMap<String, String> msg = new HashMap<String, String>();
		if(id != null) { //아이디 존재. 사용불가능한 아이디
			msg.put("status", "fail");
		}else { //사용가능한 아이디
			msg.put("status", "success");
			msg.put("id", dto.getId());
		}
		
		return msg;
	}
	
	//회원가입 post
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@ResponseBody //http 요청 body를 자바 객체로 받을 수 있도록 하는 어노테이션
	public HashMap<String, String> join(@ModelAttribute MemberDTO dto) {
		int result = memberService.join(dto); //영향 받은 행수. 성공시 1, 실패시 0
		
		HashMap<String, String> msg = new HashMap<String, String>();
		if(result>=1) { //회원가입 성공
			msg.put("status", "success");
		}else { //회원가입 실패
			msg.put("status", "fail");
		}
		return msg;
	}
}
