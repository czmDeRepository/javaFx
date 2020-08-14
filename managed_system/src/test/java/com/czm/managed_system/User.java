package com.czm.managed_system;

import java.util.Date;

/**
 * @Author CZM
 * @create 2020/7/11 16:25
 */
public class User {
    private int id;
    private String name;
    private String account;
    private String password;
    private String mailbox;
    private String phone;
    private int age;
    private int gender;
    private Date createTime;
    private int identity;
    private int enabled;

    public User() {
    }

    public User(int id, String name, String account, String password, String mailbox, String phone, int age, int gender, Date createTime, int identity, int enabled) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.mailbox = mailbox;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
        this.createTime = createTime;
        this.identity = identity;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
