package com.jiay.user.service;

import com.jiay.common.encryption.Algorithm;
import com.jiay.common.encryption.MessageDigestUtils;
import com.jiay.common.encryption.XXTEA;
import com.jiay.common.result.Code;
import com.jiay.common.result.SingleResult;
import com.jiay.common.service.BaseService;
import com.jiay.user.mapper.UserMapper;
import com.jiay.user.model.bean.UserBean;
import com.jiay.user.model.request.LoginRequest;
import com.jiay.user.model.request.RegisterRequest;
import com.jiay.user.model.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.management.VMOptionCompositeData;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 用户服务
 * @author jiay
 * @date 2020-03-17
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class UserService extends BaseService {

    @Value("${self.data.xxtea_key}")
    private String xxteaKey;

    @Value("${self.data.redis.internal_token.expire}")
    private int internal_token_expire;

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private UserMapper userMapper;

    public SingleResult<TokenResponse> login(LoginRequest request){
        List<UserBean> userList = userMapper.selectUser(request.getMobile(),MessageDigestUtils.encrypt(request.getPassword(), Algorithm.SHA1));
        if(null != userList && userList.size() > 0){
            String mobile = request.getMobile();
            String password = request.getPassword();
            //生成token存储到redis中
            String token = getToken(mobile,password);
            template.opsForValue().set(mobile,token,internal_token_expire, TimeUnit.SECONDS);
            template.opsForValue().set(token,mobile,internal_token_expire, TimeUnit.SECONDS);
            Long currentTime = System.currentTimeMillis();
            template.opsForValue().set(token+mobile,currentTime.toString());
            TokenResponse response = new TokenResponse();
            response.setToken(token);
            return SingleResult.buildSuccess(response);
        }else {
            return SingleResult.buildFailure(Code.ERROR,"手机号或密码输入不正确！");
        }
    }

    public SingleResult<TokenResponse> register(RegisterRequest request){
        String mobile = request.getMobile();
        String password = request.getPassword();
        String captcha = request.getCaptcha();
        //需要根据短信服务发送的captcha进行校验
        if(captcha.equals("1")){
            List<UserBean> userList = userMapper.selectUserByMobile(mobile);
            if(null != userList){
                if(userList.size() > 0){
                    return SingleResult.buildFailure(Code.ERROR,"已经注册！");
                }else {
                    //生成openid
                    String openid = XXTEA.encrypt(mobile+password,xxteaKey);
                    //添加用户
                    UserBean userBean = new UserBean();
                    userBean.setMobile(mobile);
                    userBean.setPassword(MessageDigestUtils.encrypt(password, Algorithm.SHA1));
                    userBean.setNickName("");
                    userBean.setOpenid(openid);
                    int ret = userMapper.addUser(userBean);
                    //响应请求
                    if(ret == 1){
                        //生成token存储到redis中
                        String token = getToken(mobile,password);
                        template.opsForValue().set(mobile,token,internal_token_expire, TimeUnit.SECONDS);
                        template.opsForValue().set(token,mobile,internal_token_expire, TimeUnit.SECONDS);
                        Long currentTime = System.currentTimeMillis();
                        template.opsForValue().set(token+mobile,currentTime.toString());
                        TokenResponse response = new TokenResponse();
                        response.setToken(token);
                        return SingleResult.buildSuccess(response);
                    }else {
                        return SingleResult.buildFailure();
                    }
                }
            }else {
                return SingleResult.buildFailure();
            }
        }else {
            return SingleResult.buildFailure(Code.ERROR,"验证码错误！");
        }
    }

}
