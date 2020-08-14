package com.czm.managed_system.service.Imp;

import com.czm.managed_system.dao.UserDao;
import com.czm.managed_system.entity.User;
import com.czm.managed_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
@Service("userService")
public class UserServiceImp implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean addAdmin(User user) {
        if (userDao.findAdminByName(user.getName()) > 0){
            return false;
        }else {
            user.setEnabled(-1);
            userDao.addUser(user);
            return true;
        }
    }

    @Override
    public boolean login(String name, String password) {
        if(userDao.countAdmin(name, password) > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int deleteAdmin(String name, String password) {
        return userDao.deleteAdmin(name, password);
    }

    @Override
    public List<User> findByDormitoryId(int dormitoryId) {
        return userDao.findByDormitoryId(dormitoryId);
    }

    @Override
    public void deleteUserByDormitoryId(int dormitoryId) {
        userDao.deleteByDormitoryId(dormitoryId);
    }
}
