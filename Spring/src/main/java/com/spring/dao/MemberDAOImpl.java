package com.spring.dao;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.spring.dto.MemberDTO;

@Repository //현재 클래스를 dao bean으로 등록
public class MemberDAOImpl implements MemberDAO {
	@Inject
	SqlSessionTemplate sqlSessionTemplate; //SqlSessionTemplate 의존관계 주입
	
	@Override
	public int login(MemberDTO dto) {
		return sqlSessionTemplate.selectOne("member.login", dto);
	}
	
	@Override
	public String idcheck(MemberDTO dto) {
		return sqlSessionTemplate.selectOne("member.idcheck", dto);
	}
	
	@Override
	//insert 쿼리의 경우 성공하면 1개의 행(row)이 생기므로 1, 실패하면 0을 리턴. 따라서 타입이 int여야 함.
	public int join(MemberDTO dto) {
		return sqlSessionTemplate.insert("member.join", dto);
	}

	@Override
	public MemberDTO info(int code) {
		return sqlSessionTemplate.selectOne("member.info", code);
	}
}
