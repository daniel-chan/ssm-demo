package com.daselearn.admin.entity;

/**
 * 图书实体
 */
public class Book {

	private Long bookId;// 图书ID

	private String name;// 图书名称

	private Integer number;// 馆藏数量

	public Book() {
	}

	public Book(Long bookId, String name, Integer number) {
		this.bookId = bookId;
		this.name = name;
		this.number = number;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Book{" +
				"bookId=" + bookId +
				", name='" + name + '\'' +
				", number=" + number +
				'}';
	}
}
