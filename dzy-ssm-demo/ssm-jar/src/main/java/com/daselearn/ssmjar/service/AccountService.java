package com.daselearn.ssmjar.service;

import com.daselearn.ssmjar.constant.GlobalConstant;
import com.daselearn.ssmjar.entity.Account;
import com.daselearn.ssmjar.mapper.AccountMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 帐号service
 *
 * @author daniel
 * @date 2019/8/21
 */

@Service
public class AccountService {

    @Resource
    private AccountMapper accountMapper;

    public PageInfo<Account> findList(Integer pageNo, Integer pageSize, String userName) {
        pageNo = pageNo == null? GlobalConstant.DEFAULT_PAGE_NO:pageNo;
        pageSize = pageSize == null?GlobalConstant.DEFAULT_PAGE_SIZE:pageSize;
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNo, pageSize);
        Account account = new Account();
        account.setNickName(userName);
        List<Account> list = accountMapper.queryBySelective(account);
        //用PageInfo对结果进行包装
        PageInfo<Account> page = new PageInfo<Account>(list);
        return page;
    }

    public void insertBatch() {
        List<Account> accounts = new ArrayList<>(10);
        Account account = new Account();
        account.setRealName("strategy");
        account.setNickName("strategy");
        account.setCardNum("53453453453453");
        account.setAssistantPassword("111111");
        account.setSex(1);
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        accounts.add(account);
        accounts.add(account);
        int cnt = accountMapper.insertBatch(accounts);
        System.out.println("insert cnt = " + cnt);
    }

    public void deleteBatch(Long[] ids) {
        int cnt = accountMapper.deleteBatch(ids);
        System.out.println("delete cnt = " + cnt);
    }
}
