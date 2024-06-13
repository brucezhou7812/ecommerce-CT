package cn.chinatelecom.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@EnableOpenApi
public class SwaggerConfiguration {
    @Bean
    public Docket webApiDoc() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("User Interface Document")
                .pathMapping("/")
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.chinatelecom"))
                .paths(PathSelectors.ant("/api/**"))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(globalRequestParameters())
                .globalResponses(HttpMethod.GET,globalResponseParameters())
                .globalResponses(HttpMethod.POST,globalResponseParameters());
    }

    @Bean
    public Docket adminApiDoc() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("Admin Interface Document")
                .pathMapping("/")
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.chinatelecom"))
                .paths(PathSelectors.ant("/admin/**"))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(globalRequestParameters())
                .globalResponses(HttpMethod.GET,globalResponseParameters())
                .globalResponses(HttpMethod.POST,globalResponseParameters());
    }

    private List<Response> globalResponseParameters(){
        List<Response> parameters = new ArrayList<>();
        parameters.add(new ResponseBuilder()
                .code("4xx")
                .description("Request error: Check the returned code and message.")
                .build());
        return parameters;
    }

    private List<RequestParameter> globalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("token")
                .description("login token")
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());

        return parameters;

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("China telecom eCommerce platform ")
                .description("Micro service interface document")
                .contact(new Contact("Bruce zhou", "http://xxx.com", "18911717812@189.cn"))
                .version("v1.0")
                .build();
    }
}
