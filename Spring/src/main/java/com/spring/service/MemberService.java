package com.spring.service;

import javax.servlet.http.HttpSession;

import com.spring.dto.MemberDTO;

public interface MemberService {
	public int login(MemberDTO dto, HttpSession session);
	public void logout(HttpSession session);
	public String idcheck(MemberDTO dto);
	public int join(MemberDTO dto);
}
