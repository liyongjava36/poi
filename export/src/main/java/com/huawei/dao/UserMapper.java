package com.huawei.dao;

import com.huawei.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    public List<User> findAll();

}
