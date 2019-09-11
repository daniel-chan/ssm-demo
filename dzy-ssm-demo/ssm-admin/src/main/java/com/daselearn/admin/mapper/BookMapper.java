package com.daselearn.admin.mapper;

import com.daselearn.admin.entity.Book;

import java.util.List;

public interface BookMapper {

	/**
	 * 通过ID查询单本图书
	 * 
	 * @param id
	 * @return
	 */
	Book queryById(long id);

	/**
	 * 插入图书
	 * @param book
	 * @return 如果影响行数大于0，插入成功
	 */
	int insert(Book book);

	/**
	 * 更新指定字段图书
	 * @param book
	 * @return 如果影响行数大于0，更新成功
	 */
	int updateByPrimaryKeySelective(Book book);

	/**
	 * 减少馆藏数量
	 * 
	 * @param bookId
	 * @return 如果影响行数等于>1，表示更新的记录行数
	 */
	int reduceNumber(long bookId);

	/**
	 * 按图书ID删除
	 * @param bookId
	 * @return 如果影响行数大于0，插入成功
	 */
	int deleteByPrimaryKey(Long bookId);

	/**
	 * 根据条件查询图书
	 * @param book
	 * @return 返回列表
	 */
    List<Book> queryBySelective(Book book);

}
