/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.rs.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * <b><code>CorsConfig</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/22 18:24
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-rs 0.1.0
 */
@Configuration
public class CorsConfig {
    /**
     * Build config CORS configuration.
     *
     * @return the CORS configuration
     * @since road-query-engine-rs 0.1.0
     */
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1.允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 2.允许任何头
        corsConfiguration.addAllowedMethod("*"); // 3.允许任何方法（post、get等）
        return corsConfiguration;
    }

    /**
     * Cors filter
     *
     * @return the CORS filter
     * @since road-query-engine-rs 0.1.0
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
