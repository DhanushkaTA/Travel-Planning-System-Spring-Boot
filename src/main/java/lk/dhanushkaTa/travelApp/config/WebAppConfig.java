package lk.dhanushkaTa.travelApp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebAppConfig {

    @Bean
    public ModelMapper setModelMapper(){
        return new ModelMapper();
    }
}
