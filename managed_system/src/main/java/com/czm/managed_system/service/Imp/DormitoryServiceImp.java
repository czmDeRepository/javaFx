package com.czm.managed_system.service.Imp;

import com.czm.managed_system.dao.DormitoryDao;
import com.czm.managed_system.dao.UserDao;
import com.czm.managed_system.entity.Dormitory;
import com.czm.managed_system.service.DormitoryService;
import org.apache.ibatis.executor.ExecutorException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
@Service
public class DormitoryServiceImp implements DormitoryService {

    @Resource
    DormitoryDao dormitoryDao;

    @Resource
    UserDao userDao;
    @Override
    public List<Dormitory> findAll() {
        return dormitoryDao.findAll();
    }

    @Override
    public List<Dormitory> findByType(int type) {
        return dormitoryDao.findByType(type);
    }

    @Override
    public Dormitory findByName(String name) {
        return dormitoryDao.findByName(name);
    }

    @Override
    public void insert(Dormitory dormitory) {
        dormitoryDao.insert(dormitory);
    }

    @Override
    public int deleteByName(String name) {
        return dormitoryDao.deleteByName(name);
    }

    @Override
    public boolean update(Dormitory dormitory) {
        if (dormitory.getStage() == 1){
            dormitory.setStartTime(new Date());
        }
        try{
            dormitoryDao.update(dormitory);
            if (dormitory.getStage() != 1) {
                dormitoryDao.reSet(dormitory.getName(),dormitory.getStage());
                userDao.deleteByDormitoryId(dormitory.getId());
            }
        }catch (MyBatisSystemException e){
            System.out.println("不存在此宿舍！");
            return false;
        }
        return true;
    }

    @Override
    public void reSet(String name,int stage) {
        dormitoryDao.reSet(name,stage);
    }
}
