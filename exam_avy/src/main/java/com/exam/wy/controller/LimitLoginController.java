package com.exam.wy.controller;

import com.exam.wy.service.Impl.LimitLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Classname LimitLoginController
 * @Author wangyun
 * @Description TODO
 * @Date 2019/7/18 0:18
 */
@Controller
@RequestMapping("login")
public class LimitLoginController {
    @Autowired
    private LimitLoginServiceImpl limitLoginService;

    @RequestMapping("limit")   // login/limit
    public String limitLogin(@RequestParam("uname")String uname,@RequestParam("upass")String upass,
        Map<String,Object> map){
            String result=limitLoginService.limitLogin(uname,upass);
            map.put("limitLogin",result);
        return "/login.jsp";
    }
}
