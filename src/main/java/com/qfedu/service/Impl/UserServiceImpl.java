package com.qfedu.service.Impl;

import com.qfedu.dao.UserDao;
import com.qfedu.entity.Admin;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Admin adminLogin(String username, String password) {
        Admin admin = userDao.adminFindByCode(username);
        if (null == admin) {
            throw new RuntimeException("账号不存在！");
        }
        if (!password.equals(admin.getPassword())) {
            throw new RuntimeException("密码错误!");
        }
        return admin;
    }

    @Override
    public User userLogin(String username, String password) {
        User user = userDao.userFindByCode(username);
        if (null == user) {
            throw new RuntimeException("用户不存在！");
        }
        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("密码错误!");
        }
        return user;
    }

    @Override
    public void update(User user) {
        userDao.updata(user);
    }

    @Override
    public void updatePassword(User user) {
        userDao.updatePassword(user);
    }

    @Override
    public void updataImgUrl(User user) {
        userDao.updataImgUrl(user);
    }
}
