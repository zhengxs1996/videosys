package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.Admin;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import com.qfedu.utils.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    public JsonResult login(Integer role, String username, String password, HttpSession session) {
        try {
            if (role == 0) {
                Admin loginUser = userService.adminLogin(username, password);
                session.setAttribute(StrUtil.LONGIN_USER, loginUser);
                return new JsonResult(1, "backstage.jsp");
            } else {
                User loginUser = userService.userLogin(username, password);
                session.setAttribute(StrUtil.LONGIN_USER, loginUser);
                return new JsonResult(1, "before/index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/exit.do")
    public String exit(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/login.html";
    }

    @RequestMapping("/user/loginUser.do")
    @ResponseBody
    public String quickLogin(String email, String password, HttpSession session) {
        User loginUser = userService.userLogin(email, password);
        session.setAttribute(StrUtil.LONGIN_USER, loginUser);
        return "success";
    }

    @RequestMapping("/user/loginOut2.do")
    public String loginOut(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/before/index.jsp";
    }

    @RequestMapping("/user/showMyProfile.do")
    public String showMyProfile(Model model, HttpSession session) {
        User LoginUser = (User) session.getAttribute(StrUtil.LONGIN_USER);
        model.addAttribute("user", LoginUser);
        return "before/my_profile";
    }

    @RequestMapping("/user/changeProfile.do")
    public String changeProfile(Model model, HttpSession session) {
        User LoginUser = (User) session.getAttribute(StrUtil.LONGIN_USER);
        model.addAttribute("user", LoginUser);
        return "before/change_profile";
    }

    @RequestMapping("/user/updateUser.do")
    public String updateUser(User user) {
        try {
            userService.update(user);
            return "forward:user/showMyProfile.do";
        } catch (Exception e) {
            return "before/change_profile";
        }
    }

    @RequestMapping("/user/updatePassword.do")
    @ResponseBody
    public JsonResult passwordSafe(String newPassword, HttpSession session) {
        try {
            User loginUser = (User) session.getAttribute(StrUtil.LONGIN_USER);
            loginUser.setPassword(newPassword);
            userService.updatePassword(loginUser);
            session.setAttribute(StrUtil.LONGIN_USER, loginUser);
            return new JsonResult(1, "密码修改成功");
        } catch (Exception e) {
            return new JsonResult(0, e.getMessage());
        }
    }

    @RequestMapping("/user/upLoadImage.do")
    public String upLoadImage(@RequestParam MultipartFile upfile, HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute(StrUtil.LONGIN_USER);
        String fName = user.getEmail();
        fName +=  ".jpg";
        user.setImgUrl(fName);
        session.setAttribute(StrUtil.LONGIN_USER, user);
        userService.updataImgUrl(user);

        //保存图片的目录
        String path = "D:/qianfeng/projects"+request.getContextPath() + "/src/main/webapp/img";
        File file = new File(path);
        //如果目录不存在，创建
        if(!file.exists()){
            file.mkdir();
        }

        File f = new File(path, fName);

        try {
            //上传文件
            upfile.transferTo(f);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/before/change_avatar.jsp";
    }

}
