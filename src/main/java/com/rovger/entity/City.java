package com.rovger.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description: TODO
 * @Author weijlu
 * @Date 2018/8/21 10:46
 */
@Data
@Entity
@Table(name = "city")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class City implements Serializable {
/*
    CREATE TABLE city (
        id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
        province_id INT(4) NOT NULL,
        city_name VARCHAR(10) NOT NULL,
        description VARCHAR(255) default NULL
    ) ENGINE = INNODB DEFAULT CHARSET = utf8;
*/
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 省份编号
     */
    @NonNull
    @Column(name = "province_id", nullable = false, length = 3)
    private Integer provinceId;

    /**
     * 城市名称
     */
    @NonNull
    @Column(name = "city_name", nullable = false)
    private String cityName;

    /**
     * 描述
     */
    @NonNull
    private String description;

    public static void main(String[] args) {
        /*System.out.println((int)Math.floor(53644 * 1.0/10000));
        System.out.println(15/4);*/
        String str = "ISD090774J43565765.txt";
        System.out.println(Pattern.matches("^(IS0026256L|ISD090774J).*", str));
    }
}
