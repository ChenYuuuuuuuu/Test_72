package io.chenyu.test_72.dao;

import io.chenyu.test_72.po.People;

public interface PeopleMapper {
    int deleteByPrimaryKey(Integer peopleId);

    int insert(People record);

    int insertSelective(People record);

    People selectByPrimaryKey(Integer peopleId);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);
}