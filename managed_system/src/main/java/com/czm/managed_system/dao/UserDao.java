package com.czm.managed_system.dao;

import com.czm.managed_system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
@Mapper
public interface UserDao {
    /**
     * 查询所有普通用户
     * @return
     */
    public List<User> findAll();

    /**
     * 增加用户
     * @param user
     */
    public void addUser(User user);

    /**
     * 根据用户名与密码查询管理员
     * @param name
     * @param password
     * @return
     */
    int countAdmin(@Param("name") String name, @Param("password") String password);

    /**
     *根据管理员名查询
     * @param name
     * @return
     */
    int findAdminByName(String name);

    /**
     * 注销管理员
     * @param name
     * @param password
     * @return
     */
    int deleteAdmin(@Param("name") String name, @Param("password") String password);

    /**
     * 根据宿舍id查询客户
     * @param dormitoryId
     * @return
     */
    List<User> findByDormitoryId(int dormitoryId);

    /**
     * 逻辑删除租户
     * @param DormitoryId
     * @return
     */
    int deleteByDormitoryId(int dormitoryId);
}
