package com.rovger;

import com.rovger.entity.City;
import com.rovger.dao.jpa.CityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: TODO
 * @Author weijlu
 * @Date 2018/8/24 16:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@EntityScan(basePackages = "com.rovger.entity")
@EnableJpaRepositories(basePackages= "com.rovger.dao.jpa")
public class CityMapperTest {

    @Autowired
    CityRepository cityRepository;

    @Test
    public void testInsert() {
        cityRepository.save(new City(100, "北京", "北京是中国政治中心"));
        cityRepository.save(new City(200, "上海", "上海是中国经济中心"));
        cityRepository.save(new City(300, "广州", "广州是中国文化中心"));

        Assert.assertEquals(3, cityRepository.findCityByCityName("北京"));
    }
}
