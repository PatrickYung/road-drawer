/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.rs.configs;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <b><code>SwaggerConfig</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/22 18:25
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-rs 0.1.0
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "doc")
public class SwaggerConfig extends WebMvcConfigurerAdapter implements EnvironmentAware {

    /**
     * addResourceHandlers
     *
     * 静态资源映射
     *
     * @param registry  registry
     * @since road-query-engine-rs 0.1.0
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    /**
     * setEnvironment
     *
     * 从配置文件里读相关的字段
     *
     * @param environment   environment
     * @since road-query-engine-rs 0.1.0
     */
    @Override
    public void setEnvironment(Environment environment) {

    }

    /**
     * swaggerSpringfoxDocket4KAD
     *
     * 定义了/test/.*开头的rest接口都分在了test分组里，可以通过/v2/api-docs?group=test得到定义的json
     *
     * @return springfox.documentation.spring.web.plugins.Docket
     * @see Docket
     * @since road-query-engine- 0.1.0
     */
    @Bean
    public Docket swaggerSpringfoxDocket4KAD() {

        StopWatch watch = new StopWatch();
        watch.start();
        Docket swaggerSpringMvcPlugin = new Docket(DocumentationType.SWAGGER_2)
                .groupName("road-query-engine")
                .apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.richstonedt.road.query.engine.rs"))
                .paths(PathSelectors.any())
                .build();
        watch.stop();
        return swaggerSpringMvcPlugin;
    }

    /**
     * 生成文档基本信息
     *
     * @return the api info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Road Query Engine Restful APIs")
                .version("v1.0").description("Road Query Engine Restful API")
                .build();
    }
}
