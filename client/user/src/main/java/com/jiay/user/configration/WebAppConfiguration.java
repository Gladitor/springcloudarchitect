package com.jiay.user.configration;

import com.jiay.user.interceptor.AuthorizationInterceptor;
import com.jiay.user.interceptor.InternalInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 自定义web配置
 * @author jiay
 * @date 2020-03-18
 */
@Configuration
public class WebAppConfiguration implements WebMvcConfigurer {

    @Bean
    public InternalInterceptor getInternalInterceptor(){
        return new InternalInterceptor();
    }

    @Bean
    public AuthorizationInterceptor getAuthorizationInterceptor() {return new AuthorizationInterceptor();}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInternalInterceptor()).addPathPatterns("/**").excludePathPatterns("/error");
        //registry.addInterceptor(getAuthorizationInterceptor()).addPathPatterns("/**");
    }

}
