package com.nothing.gateway.custom;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.nothing.user.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Hodor_Zhu
 * @description 鉴权决策器接口实现具体鉴权功能
 * @date: 2022/12/1 16:51
 */
@Component
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @DubboReference
    private IRoleService roleService;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        // 获取当前访问路径
        URI uri = authorizationContext.getExchange().getRequest().getURI();
        // 获取可访问当前路径的所有角色
        Object roles = "ROLE_admin";
        log.info("current request path: {}", uri.getPath());
        List<String> authorities = new ArrayList<>();
        log.info("current path authorities: {}", JSON.toJSON(authorities));
        //认证通过且角色匹配的用户可访问当前路径
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
