package com.rovger.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rovger.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author weijlu
 * @Date 2018/10/30 9:47
 */
@Component
public class DubboClient {

    @Autowired
    DubboService dubboService;
    //注入服务提供方暴露的接口，通过@Reference注解，dubbo会在扫描的时候自动代理接口，然后通过rpc调用远程服务。
    //如果用xml配置方式，需要将@Reference换成@Autowired。

    public Student getStudent(int id) {
        Student student = dubboService.getStudent(id);
        return student;
    }
}
