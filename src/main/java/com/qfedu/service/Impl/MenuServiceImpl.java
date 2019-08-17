package com.qfedu.service.Impl;

import com.qfedu.dao.MenuDao;
import com.qfedu.service.MenuService;
import com.qfedu.vo.MenuList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<MenuList> menuList() {
        return menuDao.MenuList();
    }
}
