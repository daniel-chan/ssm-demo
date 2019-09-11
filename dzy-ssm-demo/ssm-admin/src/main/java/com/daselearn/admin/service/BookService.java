package com.daselearn.admin.service;

import com.daselearn.common.dto.JsonResult;
import com.daselearn.admin.bean.web.AppointExecutionRP;
import com.daselearn.admin.mapper.AppointmentMapper;
import com.daselearn.admin.mapper.BookMapper;
import com.daselearn.admin.entity.Appointment;
import com.daselearn.admin.entity.Book;
import com.daselearn.admin.enums.AppointStateEnum;
import com.daselearn.admin.constant.GlobalConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookMapper bookMapper;

	@Autowired
	private AppointmentMapper appointmentMapper;


	public Book getById(long bookId) {
		return bookMapper.queryById(bookId);
	}

	public boolean deleteById(long bookId) {
		int cnt = bookMapper.deleteByPrimaryKey(bookId);
		return cnt == 1 ? true : false;
	}

	public JsonResult save(Book book) {
		if(book.getBookId()!=null){
			int result = bookMapper.updateByPrimaryKeySelective(book);
			return result==1?JsonResult.success(GlobalConstant.UPDATE_SUCCESS_MSG):JsonResult.error(GlobalConstant.UPDATE_FAIL_MSG);
		}else {
			int result = bookMapper.insert(book);
			return result==1?JsonResult.success(GlobalConstant.SAVE_SUCCESS_MSG):JsonResult.error(GlobalConstant.SAVE_FAIL_MSG);
		}
	}

	public PageInfo<Book> findList(Integer pageNo, Integer pageSize, String userName) {
		pageNo = pageNo == null?GlobalConstant.DEFAULT_PAGE_NO:pageNo;
		pageSize = pageSize == null?GlobalConstant.DEFAULT_PAGE_SIZE:pageSize;
		//将参数传给这个方法就可以实现物理分页了，非常简单。
		PageHelper.startPage(pageNo, pageSize);
		Book book = new Book();
		book.setName(userName);
		List<Book> list = bookMapper.queryBySelective(book);
		//用PageInfo对结果进行包装
		PageInfo<Book> page = new PageInfo<Book>(list);
		return page;
	}


	/**
	 * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	@Transactional
	public JsonResult appoint(long bookId, long studentId) {
		Book book = bookMapper.queryById(bookId);
		if(book == null){
			logger.info(AppointStateEnum.NOTEXIST_BOOK.getStateInfo());
			return JsonResult.error(AppointStateEnum.NOTEXIST_BOOK.getStateInfo());
		}
		// 减库存
		int update = bookMapper.reduceNumber(bookId);
		if (update <= 0) {// 库存不足
			logger.info(AppointStateEnum.NO_NUMBER.getStateInfo());
			return JsonResult.error(AppointStateEnum.NO_NUMBER.getStateInfo());
		}
		// 执行预约操作
		int insert = appointmentMapper.insertAppointment(bookId, studentId);
		if (insert <= 0) {// 重复预约
			logger.info(AppointStateEnum.REPEAT_APPOINT.getStateInfo());
			return JsonResult.error(AppointStateEnum.REPEAT_APPOINT.getStateInfo());
		}
		Appointment appointment = appointmentMapper.queryByKeyWithBook(bookId, studentId);
		return JsonResult.success(new AppointExecutionRP(bookId, AppointStateEnum.SUCCESS, appointment));
	}

}
