package com.nothing.gateway.custom.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Hodor_Zhu
 * @description 白名单URL
 * @date: 2022/12/1 16:22
 */
@Data
@Component
@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties(prefix="secure.ignore")
public class WhiteUrlConfig {
    private List<String> urls;
}
