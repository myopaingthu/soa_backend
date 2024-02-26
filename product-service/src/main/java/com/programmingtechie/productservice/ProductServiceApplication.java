package com.programmingtechie.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    //public WebMvcConfigurer corsConfigurer() {
	//	return new WebMvcConfigurer() {
	//		@Override
	//		public void addCorsMappings(CorsRegistry registry) {
	//			registry.addMapping("/**").allowedOrigins("http://localhost:5173/");
	//		}
	//	};
	//}

}
