package com.qfedu.dao;


import com.qfedu.entity.Admin;
import com.qfedu.entity.User;

public interface UserDao {
    /**
     * 查找指定用户名的密码
     * @param username 用户名
     * @return 找到该用户返回Admin对象
     */
    public Admin adminFindByCode(String username);

    public User userFindByCode(String email);

    public void updata(User user);

    public void updatePassword(User user);

    public void updataImgUrl(User user);
}
