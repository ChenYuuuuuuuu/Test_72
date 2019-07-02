package io.chenyu.test_72.dao;

import io.chenyu.test_72.po.Jon;

public interface JonMapper {
    int deleteByPrimaryKey(Integer jobId);

    int insert(Jon record);

    int insertSelective(Jon record);

    Jon selectByPrimaryKey(Integer jobId);

    int updateByPrimaryKeySelective(Jon record);

    int updateByPrimaryKey(Jon record);
}