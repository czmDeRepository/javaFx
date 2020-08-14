package com.czm.managed_system.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 主键id
     */
    private int id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 年龄
     */
    private int age;
    /**
     * 性别0-女 1-男
     */
    private byte gender;
    /**
     * 展示
     */
    private String genderString;
    /**
     * 电话
     */
    private String phone;
    /**
     * 状态 1-激活中 0 -已注销 -1管理员
     */
    private int enabled;
    /**
     * 注册时间
     */
    private Date registeredTime;

    /**
     * 宿舍id
     */
    private int dormitoryId;

    public void setGender(byte gender){
        genderString = gender == 1?"男":"女";
    }
}
