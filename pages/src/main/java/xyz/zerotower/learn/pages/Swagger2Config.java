package xyz.zerotower.learn.pages;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 *  Swagger2的配置必须与Application类同级目录
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    @Primary   //没有这个注解会报错
    public Docket createRestApi(){

        return new Docket(DocumentationType.SWAGGER_2)
                //文档信息对象
                .apiInfo(apiInfo())
                .select()
                //被注解的包路径
                .apis(RequestHandlerSelectors.basePackage("xyz.zerotower.learn.pages.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //标题
                .title("Spring Boot 中使用Swagger2 构建 API文档")
                //api文档描述
                .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
                //.termsOfServiceUrl("http://blog.didispace.com/")
                //作者信息
                .contact("ZeroTower")
                //版本
                .version("1.0")
                .build();
    }

}
