package com.spring.dto;

public class PageDTO {
	private int page; //���� ������ ��ȣ
	private int count; //�Խù� �� ����
	private int limit = 5; //���������� ����� �Խù� ����
	private int totalPage; //��ü ������ ��
	private int start; //����� �Խù� ���� ��ȣ
	private int limitPage = 3; //����¡ �� ǥ���� ��ȣ ����
	private int limitPageNum; //����¡ �� ǥ�õǴ� �� ��ȣ
	private int startPageNum; //����¡ �� ǥ�õǴ� ���� ��ȣ
	private boolean prev; //���� ��ư
	private boolean next; //���� ��ư
	
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
	//2���� ����. ������ �Է��� �ʿ��Ѱ� page(���� ������ ��ȣ)�� count(�Խù� �� ����)
	public void setPage(int page) {
		this.page = page;
	}
	
	public void setCount(int count) {
		this.count = count;
		calculate();
	}
	
	//����¡ ���
	private void calculate() {
		limitPageNum = (int)(Math.ceil((double)page / (double)limitPage) * limitPage); //����¡ �� ǥ�õǴ� �� ��ȣ
		startPageNum = limitPageNum - (limitPage - 1); //����¡ �� ǥ�õǴ� ���� ��ȣ
		
		// ������ ��ȣ ����
		int endPageNum = (int)Math.ceil((double)count / limit); //����¡ ������ ��ȣ
		 
		//����¡ ������ ��ȣ���� ���� ������ ��ȣ�� ������ ���� ������ ��ȣ�� �Է�
		if(limitPageNum > endPageNum) {
			limitPageNum = endPageNum; 
		}
		
		//������ ��ȣ �����
		prev = startPageNum == 1 ? false : true; //���� ��ư
		next = limitPageNum * limit >= count ? false : true; //���� ��ư
		
		start = (page - 1) * limit; //����� �Խù� ���� ��ȣ
	}
}
