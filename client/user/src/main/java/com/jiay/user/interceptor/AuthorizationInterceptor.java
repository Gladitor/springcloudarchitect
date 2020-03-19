package com.jiay.user.interceptor;

import com.jiay.common.annotation.AuthToken;
import com.jiay.common.result.Code;
import com.jiay.common.result.SingleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class AuthorizationInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //Header存放鉴权信息标识，默认是Authorization
    private String httpHeaderAuthorization = "Authorization";

    @Value("${self.data.redis.internal_token.expire}")
    private int internal_token_expire;

    @Value("${self.data.redis.internal_token_reset.expire}")
    private int internal_token_reset;

    @Autowired
    private StringRedisTemplate template;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 如果打上了AuthToken注解则需要验证token
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {
            String token = request.getHeader(httpHeaderAuthorization);
            if(!StringUtils.isEmpty(token)){
                String mobile = template.opsForValue().get(token);
                if(!StringUtils.isEmpty(mobile)){
                    Long tokeBirthTime = Long.valueOf(template.opsForValue().get(token + mobile));
                    logger.info("token Birth time is: {}", tokeBirthTime);
                    Long diff = System.currentTimeMillis() - tokeBirthTime;
                    logger.info("token is exist : {} ms", diff);
                    if (diff > internal_token_reset*1000) {
                        template.expire(token,internal_token_expire, TimeUnit.SECONDS);
                        template.expire(mobile,internal_token_expire,TimeUnit.SECONDS);
                        logger.info("Reset expire time success!");
                        Long newBirthTime = System.currentTimeMillis();
                        template.opsForValue().set(token + mobile, newBirthTime.toString());
                    }
                    return true;
                }
            }
        }else {
            return true;
        }
        response.setHeader("Content-Type", "text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            out.write(SingleResult.buildFailure(Code.ERROR,"鉴权失败！").toString());
        }finally {
            out.flush();
            out.close();
        }
        return false;
    }
}
