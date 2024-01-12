package com.nhnacademy.springmvc.controller;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/loginAction.do")
public class LoginPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.
        try {
            User loggedInUser = userService.doLogin(req.getParameter("user_id"), req.getParameter("user_password"));
            req.getSession().setMaxInactiveInterval(60 * 60);
            if (req.getSession() != null && loggedInUser != null) {
                req.getSession().setAttribute("loggedInUser", loggedInUser);
            }
        }catch (UserNotFoundException e){
            req.setAttribute("error_message", "* 로그인에 실패했습니다.");
            return "/shop/login/login_form";
        }
        return "redirect:/index.do";
    }
}
