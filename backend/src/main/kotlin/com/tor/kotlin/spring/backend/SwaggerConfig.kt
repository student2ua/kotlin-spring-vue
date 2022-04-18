package com.tor.kotlin.spring.backend;

import io.swagger.models.auth.In.HEADER
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders.AUTHORIZATION
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


/**
 * User: tor
 * Date: 08.12.2019
 * Time: 20:10
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .securitySchemes(Collections.singletonList(ApiKey(BEARER_API_KEY, AUTHORIZATION, HEADER.toValue())))
                .securityContexts(listOf(
                        SecurityContext.builder()
                                .forPaths(PathSelectors.regex("/api/mark.*"))
                                .securityReferences(mutableListOf(SecurityReference.builder()
                                        .reference(BEARER_API_KEY)
                                        .scopes(arrayOf(AuthorizationScope("global", "accessEverything")))
                                        .build()))
                                .build()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tor.kotlin.spring.backend.controllers"))
                .paths(PathSelectors.any())
/*
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/pricing.*"))
                */
                .build();
    }

    private fun getApiInfo(): ApiInfo {
        val contact = Contact("ZVG", "http://example.com", "ecwoukraine@gmail.com")
        return ApiInfoBuilder()
                .title("Mark Api Title")
                .description("Mark Api Definition")
                .version("19.9")
//                .license("Apache 2.0")
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contact)
                .build()
    }

    companion object {
        const val BEARER_API_KEY: String = "apiKey"
    }
}
