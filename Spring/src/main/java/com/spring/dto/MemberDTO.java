package com.spring.dto;

import java.util.Date;

public class MemberDTO {
	//로그인에 쓰일 변수 선언
	private int code; //회원코드
	private String id; //회원아이디
	private String pw; //회원비밀번호
	private String name; //회원이름
	private Date reg_date; //회원가입일자
	
	//getter와 setter 작성
	public int getCode() {
		return code; //code 가져오기
	}
	
	public void setCode(int code) {
		this.code = code; //code 값 설정
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
	
	//toString 오버라이딩. 객체가 가진 값들을 문자열로 만들어 리턴.
	@Override
	public String toString() {
		return "MemberDTO [code="+code+", id="+id+", pw="+pw+", name="+name+", reg_date="+reg_date+"]";
	}
}
