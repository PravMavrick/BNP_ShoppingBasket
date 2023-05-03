package com.shoppingbasket.price.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class AppConfiguration {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

}
