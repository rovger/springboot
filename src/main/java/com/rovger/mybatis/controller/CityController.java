package com.rovger.mybatis.controller;

import com.rovger.mybatis.entity.City;
import com.rovger.mybatis.dao.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: TODO
 * @Author weijlu
 * @Date 2018/8/24 16:10
 */
@RestController
@RequestMapping(value = "/jpa")
public class CityController {

    @Autowired
    CityRepository cityRepository;

    /**
     * 增
     * @param proId
     * @param cityName
     * @param desc
     * @return
     */
    @RequestMapping(value = "/addCity/{proId}/{cityName}/{desc}", method = RequestMethod.GET)
    public @ResponseBody String addCity(@PathVariable Integer proId, @PathVariable String cityName, @PathVariable String desc) {
        //by jpa method
        /*City city = new City(proId, cityName, desc);
        cityRepository.save(city);*/
        //by native sql
        cityRepository.addCityNative(proId, cityName, desc);
        City addedCity = cityRepository.findCityByProvinceId(proId);
        if (addedCity == null) return "no result";
        return addedCity.toString();
    }

    /**
     * 删
     * @param id
     * @return
     */
    @RequestMapping(value = "/delCityById/{id}")
    public @ResponseBody String delCityById(@PathVariable Long id) {
        cityRepository.deleteById(id);
        return "delete success.";
    }

    @RequestMapping(value = "/delCityByName/{cityName}")
    public @ResponseBody String delCityByName(@PathVariable String cityName) {
        cityRepository.deleteByCityName(cityName);
        return "delete success.";
    }

    /**
     * 改
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateById/{id}/{desc}")
    public @ResponseBody String updateById(@PathVariable Long id, @PathVariable String desc) {
        cityRepository.updateById(id, desc);
        return "update success.";
    }

    /**
     * 查
     * @return
     */
    @RequestMapping(value = "/findAll")
    public @ResponseBody String findAll() {
        List<City> cityList = cityRepository.findAll();
        if (cityList.isEmpty()) return "no result.";
        return cityList.toString();
    }

    @RequestMapping(value = "/getCity/{cityName}", method = RequestMethod.GET)
    public @ResponseBody String findCityByName(@PathVariable String cityName) {
        City city = cityRepository.findByCityName(cityName);
        if (city == null) return "no result";
        return city.toString();
    }


}
