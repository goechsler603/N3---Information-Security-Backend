package com.webapplication.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")   // libera CORS para todos os endpoints
                        .allowedOrigins("http://localhost:63342") // URL do seu frontend (ajuste a porta)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // métodos liberados
                        .allowedHeaders("*")  // libera todos headers
                        .allowCredentials(true); // se precisar enviar cookies/autenticação
            }
        };
    }
}
