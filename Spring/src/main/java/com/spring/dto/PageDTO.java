package com.spring.dto;

public class PageDTO {
	private int page; //현재 페이지 번호
	private int count; //게시물 총 갯수
	private int limit = 5; //한페이지에 출력할 게시물 갯수
	private int totalPage; //전체 페이지 수
	private int start; //출력할 게시물 시작 번호
	private int limitPage = 3; //페이징 시 표시할 번호 갯수
	private int limitPageNum; //페이징 시 표시되는 끝 번호
	private int startPageNum; //페이징 시 표시되는 시작 번호
	private boolean prev; //이전 버튼
	private boolean next; //다음 버튼
	
	//getter
	public int getLimit() {
		return this.limit;
	}
	
	public int getTotalPage() {
		return this.totalPage;
	}
	
	public int getStart() {
		return this.start;
	}
	
	public int getLimitPage() {
		return this.limitPage;
	}
	
	public int getLimitPageNum() {
		return this.limitPageNum;
	}
	
	public int getStartPageNum() {
		return this.startPageNum;
	}
	
	public boolean getPrev() {
		return this.prev;
	}
	
	public boolean getNext() {
		return this.next;
	}
	
	
	//setter
	//2개만 설정. 데이터 입력이 필요한건 page(현재 페이지 번호)와 count(게시물 총 갯수)
	public void setPage(int page) {
		this.page = page;
	}
	
	public void setCount(int count) {
		this.count = count;
		calculate();
	}
	
	//페이징 계산
	private void calculate() {
		limitPageNum = (int)(Math.ceil((double)page / (double)limitPage) * limitPage); //페이징 시 표시되는 끝 번호
		startPageNum = limitPageNum - (limitPage - 1); //페이징 시 표시되는 시작 번호
		
		// 마지막 번호 재계산
		int endPageNum = (int)Math.ceil((double)count / limit); //페이징 마지막 번호
		 
		//페이징 마지막 번호보다 실제 마지막 번호가 작으면 실제 마지막 번호로 입력
		if(limitPageNum > endPageNum) {
			limitPageNum = endPageNum; 
		}
		
		//페이지 번호 만들기
		prev = startPageNum == 1 ? false : true; //이전 버튼
		next = limitPageNum * limit >= count ? false : true; //다음 버튼
		
		start = (page - 1) * limit; //출력할 게시물 시작 번호
	}
}
