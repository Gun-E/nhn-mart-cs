package com.nhnacademy.springmvc.controller;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/logout.do")
public class LogoutController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-3 로그아웃 구현
        req.getSession().invalidate();
        return "redirect:/index.do";
    }
}
