package com.rovger.service;

import com.rovger.entity.City;

import java.util.List;

/**
 * @Description: TODO
 * @Author weijlu
 * @Date 2018/10/25 16:54
 */
public interface CityService {

    City findByCityName(String cityName);

    /**
     * 与findByCityName()方法结果一致，这种写法是“JpaRepository”的，目的是可以免写sql
     * @param cityName
     * @return
     */
    City findCityByCityName(String cityName);

    /**
     * 这类方法关系Entity属性的增删改查需要在mapper类中声明，简单的方法如save则不需要在该类中声明方法名
     * @param provinceId
     * @return
     */
    City findCityByProvinceId(int provinceId);

    /**
     * 利用原生SQL新增--新增城市信息, ignore用于id唯一约束重复情况下，自动忽略该次insert
     * @param provinceId
     * @param cityName
     * @param desc
     */
    int addCityNative(int provinceId, String cityName, String desc);

    /**
     * 根据cityName删除city
     * @param cityName
     */
    void deleteByCityName(String cityName);

    /**
     * 利用原生的SQL语句来更新--根据id更新数据
     * @param id
     * @param desc
     */
    void updateById(long id, String desc);

    /**
     * 根据id删除一条记录
     * @param id
     * @return
     */
    void deleteById(long id);

    /**
     * 查询所有记录
     * @return
     */
    List<City> findAll();
}
