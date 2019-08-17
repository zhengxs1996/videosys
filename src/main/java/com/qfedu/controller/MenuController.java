package com.qfedu.controller;

import com.qfedu.service.MenuService;
import com.qfedu.vo.MenuList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu.do")
    @ResponseBody
    public List<MenuList> menuList() {
        return menuService.menuList();
    }
}
