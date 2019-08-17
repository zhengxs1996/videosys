package com.qfedu.service;

import com.qfedu.entity.Admin;
import com.qfedu.entity.User;

public interface UserService {
    public Admin adminLogin(String username, String password);

    public User userLogin(String username, String password);

    public void update(User user);

    public void updatePassword(User user);

    public void updataImgUrl(User user);
}
