package com.czm.managed_system;

/**
 * @Author CZM
 * @create 2020/7/11 16:32
 */
public class Dormotiry {
    private int id;
    private String name;
    private String address;
    private int state;
    private int type;
    public Dormotiry() {
    }
    public Dormotiry(int id, String name, String address, int state, int type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.state = state;
        this.type = type;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
}
