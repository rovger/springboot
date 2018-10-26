package com.rovger.dao.jpa;

import com.rovger.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @Description: JPA方式实现数据库连接
 * @Author weijlu
 * @Date 2018/8/21 11:01
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    /**
     * 利用原生SQL查询--根据城市名称，查询城市信息
     * @param cityName
     * @return
     */
    @Query(value = "select id, province_id, city_name, description from city where city_name = ?1", nativeQuery = true)
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
    @Query(value = "insert ignore into city(province_id, city_name, description) value(?1, ?2, ?3)", nativeQuery = true)
    @Modifying
    @Transactional
    int addCityNative(int provinceId, String cityName, String desc);

    /**
     * 根据cityName删除city
     * @param cityName
     */
    @Transactional
    void deleteByCityName(String cityName);

    /**
     * 利用原生的SQL语句来更新--根据id更新数据
     * @param id
     * @param desc
     */
    @Query(value = "update city set description = ?2 where id = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    void updateById(long id, String desc);

}
