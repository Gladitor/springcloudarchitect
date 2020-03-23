package com.jiay.user.mapper;

import com.jiay.user.model.bean.UserBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into oauth_user (mobile,password,nickname,openid) values(#{mobile},#{password},#{nickName},#{openid})")
    int addUser(UserBean userBean);

    @Select("select id,mobile,password,gmt_create,gmt_modified,nickname,openid from oauth_user where mobile = #{mobile} and password = #{password}")
    List<UserBean> selectUser(@Param("mobile") String mobile, @Param("password") String password);

    @Select("select id,mobile,password from oauth_user where mobile = #{mobile}")
    List<UserBean> selectUserByMobile(String mobile);

}
