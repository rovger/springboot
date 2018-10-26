package com.rovger.service.impl;

import com.rovger.dao.mybatis.CityDao;
import com.rovger.dao.jpa.CityRepository;
import com.rovger.entity.City;
import com.rovger.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 分别使用jpa和mybatis来调用sql
 * @Author weijlu
 * @Date 2018/10/25 16:55
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityDao cityDao;

    @Override
    public City findByCityName(String cityName) {
//        return cityRepository.findByCityName(cityName);
        return cityDao.findByCityName(cityName);
    }

    @Override
    public City findCityByCityName(String cityName) {
        return cityRepository.findCityByCityName(cityName);
    }

    @Override
    public City findCityByProvinceId(int provinceId) {
        return cityRepository.findCityByProvinceId(provinceId);
    }

    @Override
    public int addCityNative(int provinceId, String cityName, String desc) {
//        return cityRepository.addCityNative(provinceId, cityName, desc);
        return cityDao.addCityNative(provinceId, cityName, desc);
    }

    @Override
    public void deleteByCityName(String cityName) {
        cityRepository.deleteByCityName(cityName);
    }

    @Override
    public void updateById(long id, String desc) {
        cityRepository.updateById(id, desc);
    }

    @Override
    public void deleteById(long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
