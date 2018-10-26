package com.rovger.dao.mybatis;

import com.rovger.entity.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Description: Mybatis annotation方式实现数据库连接
 * @Author weijlu
 * @Date 2018/10/25 16:28
 */
@Mapper
@Repository
public interface CityDao {

    //在实体中做好mapping关系，这里就不需要一一mapping
    /*@Results({
            @Result(property = "id", column = "id"),
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "description", column = "description")
    })*/
    @Select("SELECT * FROM city WHERE city_name = #{cityName}")
    City findByCityName(@Param("cityName") String cityName);

    @Insert("INSERT INTO city(province_id, city_name, description) value(#{provinceId}, #{cityName}, #{desc})")
    int addCityNative(@Param("provinceId") int provinceId, @Param("cityName") String cityName, @Param("desc") String desc);

}
