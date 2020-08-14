package com.czm.managed_system.dao;

import com.czm.managed_system.entity.Dormitory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
@Mapper
public interface DormitoryDao {

    List<Dormitory> findAll();

    List<Dormitory> findByType(int type);

    Dormitory findByName(String name);

    void insert(Dormitory dormitory);

    int deleteByName(String name);

    int update(Dormitory dormitory);

    //重置宿舍
    void reSet(@Param("name") String name, @Param("stage") int stage);

}
