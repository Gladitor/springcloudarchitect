package com.jiay.user.configration;

import com.jiay.user.interceptor.AuthorizationInterceptor;
import com.jiay.user.interceptor.InternalInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义web配置
 * @author jiay
 * @date 2020-03-18
 */
@SpringBootConfiguration
public class WebAppConfiguration implements WebMvcConfigurer {

    @Bean
    public InternalInterceptor getInternalInterceptor(){
        return new InternalInterceptor();
    }

    @Bean
    public AuthorizationInterceptor getAuthorizationInterceptor() {return new AuthorizationInterceptor();}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInternalInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(getAuthorizationInterceptor()).addPathPatterns("/**");
    }
}
