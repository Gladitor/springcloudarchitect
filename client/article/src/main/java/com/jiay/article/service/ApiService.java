package com.jiay.article.service;

import com.jiay.common.result.SingleResult;
import com.jiay.article.configration.FeignConfigration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jiay
 * @date 2020/3/20
 */
@Primary
@FeignClient(value = "eurekaclientuser",fallback = ApiServiceError.class,configuration = FeignConfigration.class)
public interface ApiService {
    @RequestMapping("user/internalAuthorize")
    SingleResult internalAuthorize();
}
