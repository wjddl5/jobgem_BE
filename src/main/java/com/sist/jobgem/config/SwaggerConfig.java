package com.sist.jobgem.config;

import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userGroup() {
        List<Tag> tags = List.of(
                new Tag().name("UserController").description("유저"));

        return GroupedOpenApi.builder()
                .group("user")
                .pathsToMatch("/user/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.setTags(tags);
                })
                .build();
    }

    @Bean
    public GroupedOpenApi commonGroup() {
        List<Tag> tags = List.of(
                new Tag().name("CommonController").description("공용 컨트롤러"));

        return GroupedOpenApi.builder()
                .group("common")
                .pathsToMatch("/common/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.setTags(tags);
                })
                .build();
    }

    @Bean
    public GroupedOpenApi companyGroup() {
        return GroupedOpenApi.builder()
                .group("company")
                .pathsToMatch("/api/company/**")
                .build();
    }

    @Bean
    public GroupedOpenApi jobseekerGroup() {
        return GroupedOpenApi.builder()
                .group("jobseeker")
                .pathsToMatch("/api/jobseeker/**")
                .build();
    }

    @Bean
    public GroupedOpenApi postGroup() {
        return GroupedOpenApi.builder()
                .group("posts")
                .pathsToMatch("/api/posts/**")
                .build();
    }

    @Bean
    public GroupedOpenApi fileGroup() {
        return GroupedOpenApi.builder()
                .group("files")
                .pathsToMatch("/api/files/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminGroup() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/api/admin/**")
                .build();
    }

}
