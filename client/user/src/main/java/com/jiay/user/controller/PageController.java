package com.jiay.user.controller;

import com.jiay.common.controller.BaseController;
import com.jiay.user.model.authorize.in.AuthorizeIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jiay
 * @date 2020/3/20
 */
@RequestMapping("")
@Controller
public class PageController extends BaseController {
    @GetMapping("login")
    public String login(AuthorizeIn authorize, Model model){
        model.addAttribute("authorize",authorize);
        return "login";
    }
}
