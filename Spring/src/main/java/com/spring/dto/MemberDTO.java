package com.spring.dto;

import java.util.Date;

public class MemberDTO {
	//�α��ο� ���� ���� ����
	private int code; //ȸ���ڵ�
	private String id; //ȸ�����̵�
	private String pw; //ȸ����й�ȣ
	private String name; //ȸ���̸�
	private Date reg_date; //ȸ����������
	
	//getter�� setter �ۼ�
	public int getCode() {
		return code; //code ��������
	}
	
	public void setCode(int code) {
		this.code = code; //code �� ����
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getRegDate() {
		return reg_date;
	}
	
	public void setRegDate(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	//toString �������̵�. ��ü�� ���� ������ ���ڿ��� ����� ����.
	@Override
	public String toString() {
		return "MemberDTO [code="+code+", id="+id+", pw="+pw+", name="+name+", reg_date="+reg_date+"]";
	}
}
