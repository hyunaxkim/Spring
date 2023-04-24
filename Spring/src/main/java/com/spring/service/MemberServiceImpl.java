
package com.spring.service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.spring.dao.MemberDAO;
import com.spring.dto.MemberDTO;

@Service //service bean���� ���
public class MemberServiceImpl implements MemberService {
	@Inject
	MemberDAO memberDao;
	
	@Override
	public int login(MemberDTO dto, HttpSession session) {
		int code = memberDao.login(dto); //�α���
		
		MemberDTO member = memberDao.info(code); //ȸ���ڵ�� ȸ������ ��������
		String name = member.getName();
		if(name != null) {
			//���� ���� ����
			session.setAttribute("code", code); //ȸ���ڵ�
			session.setAttribute("name", name); //ȸ���̸�
		}
		return code;
	}
	
	@Override
	public void logout(HttpSession session) {
		session.invalidate(); //���� �ʱ�ȭ
	}
	
	@Override
	public String idcheck(MemberDTO dto) {
		String id = memberDao.idcheck(dto); //���̵� üũ
		return id;
	}
	
	@Override
	public int join(MemberDTO dto) {
		return memberDao.join(dto); //ȸ������
	}
}
