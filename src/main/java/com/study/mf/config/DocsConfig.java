package com.study.mf.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocsConfig {

    @Bean
    OpenAPI swaggerConfigs(){
        return new OpenAPI().info(
            new Info()
                .title("Music 90's Library")
                .description("API to manager 90's music")
                .version("v1")
                .license(new License().name("Apache 2.0"))
        );
    }
}
