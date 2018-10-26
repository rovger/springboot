package com.rovger.controller;

import com.rovger.entity.City;
import com.rovger.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 分别根据jpa和mybatis来实现对数据库的访问
 * @Author weijlu
 * @Date 2018/8/24 16:10
 */
@RestController
@RequestMapping(value = "/ops")
public class CityController {

    @Autowired
    CityService cityService;

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
        cityService.save(city);*/
        //by native sql
        cityService.addCityNative(proId, cityName, desc);
        City addedCity = cityService.findCityByProvinceId(proId);
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
        cityService.deleteById(id);
        return "delete success.";
    }

    @RequestMapping(value = "/delCityByName/{cityName}")
    public @ResponseBody String delCityByName(@PathVariable String cityName) {
        cityService.deleteByCityName(cityName);
        return "delete success.";
    }

    /**
     * 改
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateById/{id}/{desc}")
    public @ResponseBody String updateById(@PathVariable Long id, @PathVariable String desc) {
        cityService.updateById(id, desc);
        return "update success.";
    }

    /**
     * 查
     * @return
     */
    @RequestMapping(value = "/findAll")
    public @ResponseBody String findAll() {
        List<City> cityList = cityService.findAll();
        if (cityList.isEmpty()) return "no result.";
        return cityList.toString();
    }

    @RequestMapping(value = "/getCity/{cityName}", method = RequestMethod.GET)
    public @ResponseBody String findCityByName(@PathVariable String cityName) {
        City city = cityService.findByCityName(cityName);
        if (city == null) return "no result";
        return city.toString();
    }


}
