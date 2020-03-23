package com.jiay.user.controller;

import com.jiay.common.annotation.AuthToken;
import com.jiay.common.controller.BaseController;
import com.jiay.common.result.Code;
import com.jiay.common.result.SingleResult;
import com.jiay.user.model.request.LoginRequest;
import com.jiay.user.model.request.RegisterRequest;
import com.jiay.user.model.response.TokenResponse;
import com.jiay.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 用户控制器
 * @author jiay
 * @date 2020-03-17
 */
@RequestMapping("user")
@RestController
public class UserController extends BaseController {

    @Value("${self.data.openid_session_attribute}")
    private String sessionOpenIdAttribute;

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public SingleResult<TokenResponse> login(@Valid @RequestBody LoginRequest request, BindingResult result){
        validate(result);
        return userService.login(request);
    }

    @RequestMapping("register")
    public SingleResult<TokenResponse> register(@Valid @RequestBody RegisterRequest request,BindingResult result){
        validate(result);
        return userService.register(request);
    }

    @AuthToken
    @RequestMapping("internalAuthorize")
    public SingleResult internalAuthorize(){
        return SingleResult.buildSuccess(Code.SUCCESS,"鉴权成功！");
    }

    @RequestMapping("authorizelogin")
    public SingleResult<TokenResponse> login(@Valid @RequestBody LoginRequest request, BindingResult result, HttpServletRequest httpServletRequest){
        validate(result);
        SingleResult<TokenResponse> singleResult = userService.login(request);
        if(singleResult.getCode() == Code.SUCCESS.getStatus()){
            WebUtils.setSessionAttribute(httpServletRequest,sessionOpenIdAttribute,singleResult.getData().getOpenId());
        }
        return singleResult;
    }


}
