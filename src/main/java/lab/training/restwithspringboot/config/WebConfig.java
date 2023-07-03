package lab.training.restwithspringboot.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lab.training.restwithspringboot.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");
    @Value("${cors.originPatterns:http://localhost:8080,http://localhost:3000}")
    private String corsOriginPatterns = "";

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMessageConverter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrigins = corsOriginPatterns.split(",");
        registry.addMapping("/**")
                //.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedMethods("*")
                .allowedOrigins(allowedOrigins)
                .allowCredentials(true);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        // Via EXTENSION. localhost:8080/person.x-yaml
        /*
         * configurer.favorParameter(false) .ignoreAcceptHeader(false)
         * .defaultContentType(MediaType.APPLICATION_JSON) .mediaType("json",
         * MediaType.APPLICATION_JSON) .mediaType("xml", MediaType.APPLICATION_XML);
         */

        // Via QUERY PARAM. localhost:8080/person?mediaType=xml
        /*
         * configurer.favorPathExtension(false) .favorParameter(true)
         * .parameterName("mediaType") .ignoreAcceptHeader(true)
         * .useRegisteredExtensionsOnly(false)
         * .defaultContentType(MediaType.APPLICATION_JSON) .mediaType("json",
         * MediaType.APPLICATION_JSON) .mediaType("xml", MediaType.APPLICATION_XML);
         */

        configurer.favorPathExtension(false)
                .favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("x-yaml", MEDIA_TYPE_YML);
    }

}
