package com.nothing.gateway.custom.config;

import cn.hutool.core.util.ArrayUtil;
import com.nothing.common.constants.CommonConstant;
import com.nothing.gateway.custom.AuthorizationManager;
import com.nothing.gateway.custom.filter.WhiteUrlsRemoveJwtFilter;
import com.nothing.gateway.custom.handler.RestAccessDeniedHandler;
import com.nothing.gateway.custom.handler.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
/**
 * @author: Hodor_Zhu
 * @description 对网关服务进行安全信息配置，并使用注解开启
 * @date: 2022/12/1 16:20
 */
@EnableWebFluxSecurity
@Configuration
public class ResourceServerConfig {
    @Autowired
    private AuthorizationManager authorizationManager;

    @Autowired
    private WhiteUrlConfig whiteUrlsConfig;

    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private WhiteUrlsRemoveJwtFilter whiteUrlsRemoveJwtFilter;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        //自定义处理JWT请求头过期或签名错误的结果
        http.oauth2ResourceServer().authenticationEntryPoint(restAuthenticationEntryPoint);
        //对白名单路径，直接移除JWT请求头
        http.addFilterBefore(whiteUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        http.authorizeExchange()
                .pathMatchers(ArrayUtil.toArray(whiteUrlsConfig.getUrls(), String.class)).permitAll() // 白名单配置
                .anyExchange().access(authorizationManager)                        // 鉴权管理器配置
                .and().exceptionHandling()
                .accessDeniedHandler(restAccessDeniedHandler)                      // 处理未授权异常
                .authenticationEntryPoint(restAuthenticationEntryPoint)            // 处理未认证异常
                .and().csrf().disable();
        return http.build();
    }

    // JWT解析器
    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        /**设置 jwtGrantedAuthoritiesConverter.setAuthorityPrefix()的内容可以视 sys_role 表 code 字段的具体情况而定
        能够与Spring Security默认的角色标识是 ROLE 开头而Oauth2默认的角色标识是 SCOPE 匹配即可*/
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(CommonConstant.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
