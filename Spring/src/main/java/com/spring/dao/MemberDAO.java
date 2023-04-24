package com.spring.dao;

import com.spring.dto.MemberDTO;

public interface MemberDAO {
	public int login(MemberDTO dto);
	public String idcheck(MemberDTO dto);
	public int join(MemberDTO dto);
	public MemberDTO info(int code);
}
