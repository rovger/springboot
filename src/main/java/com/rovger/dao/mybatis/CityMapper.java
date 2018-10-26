package com.rovger.dao.mybatis;

import com.rovger.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: Mybatis xml方式实现数据库连接
 * @Author weijlu
 * @Date 2018/10/26 16:18
 */
@Mapper
@Repository
public interface CityMapper {

    /**
     * 用mapper的方式来update数据
     * @param city
     */
    void updateById(City city);
}
