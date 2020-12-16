package cn.edu.zjut.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Swagger2API文档的配置
 * Created by iris on 2020/12/16.
 */
@EnableWebMvc
@Configuration //说明这是一个配置类
@EnableSwagger2 //开启Swagger2的自动配置
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包下controller生成API文档
                .apis(RequestHandlerSelectors.basePackage("cn.edu.zjut.common.controller"))
                //为有@Api注解的Controller生成API文档
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //为有@ApiOperation注解的方法生成API文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /*    private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("Campus-activity")
                    .description("Campus-activity在线API文档")
                    .version("1.0")
                    .build();
        }*/
    private ApiInfo apiInfo() {
        Contact contact = new Contact("浙江工业大学", "http://www.zjut.edu.cn/", "xx@zjut.edu.cn");
        return new ApiInfo(
                "Campus-activity", // 标题
                "Campus-activity在线API文档", // 描述
                "v1.0", // 版本
                "http://www.zjut.edu.cn/", // 组织链接
                contact,
                "Apach 2.0 许可", // 许可
                "许可链接"// 许可链接
        );
    }
}
