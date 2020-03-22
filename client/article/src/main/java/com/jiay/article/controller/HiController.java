package com.jiay.article.controller;

import com.jiay.article.service.ApiService;
import com.jiay.common.result.Code;
import com.jiay.common.result.SingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiay
 * @date 2020/3/21
 */
@RestController
public class HiController {

    @Autowired
    private ApiService apiService;

    @RequestMapping("hi")
    public String hi(){
        SingleResult result = apiService.internalAuthorize();
        if(result.getCode() == Code.SUCCESS.getStatus()){
            return "hi,authorize success";
        }else {
            return "hi,authorize faild";
        }

    }
}
