package uz.pdp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any()) //RequestHandlerSelectors.any() bo'lsa project dagi hamma controllerlarni va o'zidagi base-error-controller ni ham ko'rsatadi
                .apis(RequestHandlerSelectors.basePackage("uz.pdp.controller")) //RequestHandlerSelectors.basePackage() bo'lsa project dagi berilgan package dagi hamma controllerlarni chiqaradi va o'zidagi base-error-controller ni ko'rsatmaydi
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    //Swagger UI da tepada chiqib turadigan qismni projectga moslashtirish
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("WAREHOUSE API").description("04.03.2022")
                .contact(new Contact("PDP Bootcamp Team", "https://pdp.uz/", ""))
                .licenseUrl("\"https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
