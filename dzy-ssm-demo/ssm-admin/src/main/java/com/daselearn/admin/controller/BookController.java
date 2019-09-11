package com.daselearn.admin.controller;

import com.daselearn.common.dto.JsonResult;
import com.daselearn.admin.entity.Book;
import com.daselearn.admin.service.BookService;
import com.daselearn.admin.constant.GlobalConstant;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Api(value = "管理后台", description = "图书管理")
@RequestMapping("/book") // url:/模块/资源/{id}/细分 /book/list
public class BookController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ApiOperation(value = "删除图书", httpMethod = "POST", response = JsonResult.class, notes = "删除图书")
	@ResponseBody
	private JsonResult del(Model model,Long bookId) {
		boolean success = bookService.deleteById(bookId);
		logger.debug(GlobalConstant.DELETE_SUCCESS_MSG);
		return success?JsonResult.success(GlobalConstant.DELETE_SUCCESS_MSG):JsonResult.error(GlobalConstant.DELETE_FAIL_MSG);
	}

	@RequestMapping(value = "/save")
	@ApiOperation(value = "保存图书", httpMethod = "POST", response = JsonResult.class, notes = "保存图书")
	@ResponseBody
	private JsonResult save(Model model,Book book) {
		logger.debug(GlobalConstant.SAVE_SUCCESS_MSG);
		return bookService.save(book);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "图书列表", httpMethod = "GET", response = JsonResult.class, notes = "图书列表")
	@ResponseBody
	private JsonResult list(Model model, Integer pageNo,Integer pageSize,String userName) {
		PageInfo<Book> page = bookService.findList(pageNo,pageSize,userName);
		System.out.println(page);
		if(logger.isDebugEnabled()){
			logger.debug("获取图书列表");
		}
		return JsonResult.success(page);
	}

	@RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
	@ApiOperation(value = "查看图书", httpMethod = "GET", response = JsonResult.class, notes = "查看图书")
	@ResponseBody
	private JsonResult detail(@PathVariable("bookId") Long bookId) {
		if (bookId == null) {
			return JsonResult.error("参数错误");
		}
		Book book = bookService.getById(bookId);
		if (book == null) {
			return JsonResult.error("没有对应图书");
		}
		return JsonResult.success(book);
	}

	// Ajax返回json字符串String时,若指定了application/json返回json对象.
//	@RequestMapping(value = "/{bookId}/appoint", method = RequestMethod.POST, produces = {"application/json; charset=utf-8" })
//	@RequestMapping(value = "/{bookId}/appoint", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )  //用法与上面等价
	@RequestMapping(value = "/{bookId}/appoint", method = RequestMethod.POST)
	@ApiOperation(value = "预约图书", httpMethod = "POST", response = JsonResult.class, notes = "预约图书")
	@ResponseBody
	private JsonResult appoint(@PathVariable("bookId") Long bookId, @RequestParam("studentId") Long studentId) {
		if (studentId == null || studentId.equals("")) {
			return   JsonResult.error("学号不能为空");
		}
		return bookService.appoint(bookId, studentId);
	}


}
