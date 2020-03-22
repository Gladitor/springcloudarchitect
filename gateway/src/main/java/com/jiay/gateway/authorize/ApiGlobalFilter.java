package com.jiay.gateway.authorize;

import com.jiay.common.result.Code;
import com.jiay.common.result.SingleResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author jiay
 * @date 2020/3/20
 */
@Component
public class ApiGlobalFilter implements GlobalFilter {

    //Header存放鉴权信息标识，默认是Authorization
    private String httpHeaderAuthorization = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //String token = exchange.getRequest().getQueryParams().getFirst("token");
        String token = exchange.getRequest().getHeaders().getFirst(httpHeaderAuthorization);
        if (StringUtils.isBlank(token)) {
            ServerHttpResponse response = exchange.getResponse();
            SingleResult singleResult = SingleResult.buildFailure(Code.ERROR,"鉴权失败！");
            byte[] bits = singleResult.toString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add("Content-Type", "text/json;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }
}
