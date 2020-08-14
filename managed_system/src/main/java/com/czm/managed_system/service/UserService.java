package com.czm.managed_system.service;

import com.czm.managed_system.entity.User;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
public interface UserService {

    void addUser(User user);

    List<User> findAll();

    boolean addAdmin(User user);

    boolean login(String name,String password);

    int deleteAdmin(String name,String password);

    List<User> findByDormitoryId(int dormitoryId);

    void deleteUserByDormitoryId(int dormitoryId);
}
