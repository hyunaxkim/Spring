
package com.spring.service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.spring.dao.MemberDAO;
import com.spring.dto.MemberDTO;

@Service //service bean으로 등록
public class MemberServiceImpl implements MemberService {
	@Inject
	MemberDAO memberDao;
	
	@Override
	public int login(MemberDTO dto, HttpSession session) {
		int code = memberDao.login(dto); //로그인
		
		MemberDTO member = memberDao.info(code); //회원코드로 회원정보 가져오기
		String name = member.getName();
		if(name != null) {
			//세션 변수 저장
			session.setAttribute("code", code); //회원코드
			session.setAttribute("name", name); //회원이름
		}
		return code;
	}
	
	@Override
	public void logout(HttpSession session) {
		session.invalidate(); //세션 초기화
	}
	
	@Override
	public String idcheck(MemberDTO dto) {
		String id = memberDao.idcheck(dto); //아이디 체크
		return id;
	}
	
	@Override
	public int join(MemberDTO dto) {
		return memberDao.join(dto); //회원가입
	}
}
