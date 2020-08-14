package com.czm.managed_system.service;

import com.czm.managed_system.entity.Dormitory;

import java.util.List;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
public interface DormitoryService {
    List<Dormitory> findAll();

    List<Dormitory> findByType(int type);

    Dormitory findByName(String name);

    void insert(Dormitory dormitory);

    int deleteByName(String name);

    boolean update(Dormitory dormitory);

    void reSet(String name,int stage);

}
