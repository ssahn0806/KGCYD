package com.kgc.chatbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        final ApiInfo apiInfo = new ApiInfoBuilder()
                .title("KGC영동점 - 관리자서버")
                .description("<h3>ChatBot API에 대한 문서를 제공한다.</h3>")
                .license("MIT License")
                .version("1.0")
                .build();

        return new Docket(DocumentationType.OAS_30)        // 
                .apiInfo(apiInfo)                             // 문서에 대한 정보를 설정한다.
                .select()                                    // ApiSelectorBuilder를 반환하며 상세한 설정 처리
                .apis(RequestHandlerSelectors.basePackage("com.kgc.chatbot.controller"))// 대상으로하는 api 설정
                .paths(PathSelectors.ant("/api/**"))    // controller에서 swagger를 지정할 대상 path 설정
                .build();                                    // Docket 객체 생성            
    }
    
    @Bean
    public UiConfiguration uiConfig() {
    	return UiConfigurationBuilder.builder().supportedSubmitMethods(new String[] {"get"}).build();
    }
}
