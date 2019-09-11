package com.daselearn.ssmjar;

import com.daselearn.ssmjar.constant.GlobalConstant;
import com.daselearn.ssmjar.entity.Account;
import com.daselearn.ssmjar.mapper.AccountMapper;
import com.daselearn.ssmjar.service.AccountService;
import com.daselearn.ssmjar.util.SpringCtxHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 启动类
 *
 * @author daniel
 * @date 2019/8/21
 */
public class App {

    private final static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        AccountMapper accountMapper = SpringCtxHelper.getCtx().getBean(AccountMapper.class);
        AccountService accountService  = SpringCtxHelper.getCtx().getBean(AccountService.class);
        Account account = new Account();
        account.setRealName("strategy");
        account.setNickName("strategy");
        account.setCardNum("53453453453453");
        account.setAssistantPassword("111111");
        account.setSex(0);
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        //插入单条
        accountMapper.insertSelective(account);
        //查询单条
        Account a = accountMapper.selectByPrimaryKey(19L);
        System.out.println(a.getNickName()==null);

        //查询分页
        PageInfo<Account> page = accountService.findList(GlobalConstant.DEFAULT_PAGE_NO,GlobalConstant.DEFAULT_PAGE_SIZE,null);
        System.out.println("page = " + page);

        //批量插入
        accountService.insertBatch();

        //批量删除
        Long[] ids = new Long[]{1L,2L};
        accountService.deleteBatch(ids);
    }

}
