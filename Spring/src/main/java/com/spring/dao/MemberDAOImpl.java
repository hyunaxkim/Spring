package com.spring.dao;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.spring.dto.MemberDTO;

@Repository //���� Ŭ������ dao bean���� ���
public class MemberDAOImpl implements MemberDAO {
	@Inject
	SqlSessionTemplate sqlSessionTemplate; //SqlSessionTemplate �������� ����
	
	@Override
	public int login(MemberDTO dto) {
		return sqlSessionTemplate.selectOne("member.login", dto);
	}
	
	@Override
	public String idcheck(MemberDTO dto) {
		return sqlSessionTemplate.selectOne("member.idcheck", dto);
	}
	
	@Override
	//insert ������ ��� �����ϸ� 1���� ��(row)�� ����Ƿ� 1, �����ϸ� 0�� ����. ���� Ÿ���� int���� ��.
	public int join(MemberDTO dto) {
		return sqlSessionTemplate.insert("member.join", dto);
	}

	@Override
	public MemberDTO info(int code) {
		return sqlSessionTemplate.selectOne("member.info", code);
	}
}
