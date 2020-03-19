package com.jiay.user.mapper;

import com.jiay.user.model.authorize.out.ApplicationOut;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ApplicationMapper {

    @Select("select client_id,secret from oauth_application where client_id = #{clientId}")
    ApplicationOut findByClientId(@Param("clientId") String clientId);

    @Select("select client_id,secret from oauth_application where client_id = #{clientId} and secret = #{secret}")
    ApplicationOut findByClientIdAndSecret(@Param("clientId") String clientID, @Param("secret") String secret);
}
