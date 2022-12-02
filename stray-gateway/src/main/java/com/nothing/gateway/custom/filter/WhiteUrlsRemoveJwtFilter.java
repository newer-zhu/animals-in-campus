package com.nothing.gateway.custom.filter;

import com.nothing.gateway.custom.config.WhiteUrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

/**
 * @author: Hodor_Zhu
 * @description
 * @date: 2022/12/1 16:29
 */
@Component
public class WhiteUrlsRemoveJwtFilter implements WebFilter {
    @Autowired
    private WhiteUrlConfig whiteUrlsConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 获取当前路径
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        List<String> whiteUrls = whiteUrlsConfig.getUrls();
        for (String url : whiteUrls) {
            // 若为白名单路径则移除JWT请求头
            if (pathMatcher.match(url, uri.getPath())) {
                request = exchange.getRequest().mutate().header("Authorization", "").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }
}
