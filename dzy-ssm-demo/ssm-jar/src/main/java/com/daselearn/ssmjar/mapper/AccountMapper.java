package com.daselearn.ssmjar.mapper;


import com.daselearn.ssmjar.entity.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {

    int deleteByPrimaryKey(Long userId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    List<Account> queryBySelective(Account account);

    int insertBatch(@Param("accounts") List<Account> accounts);

    int deleteBatch(Long[] idArr);
}