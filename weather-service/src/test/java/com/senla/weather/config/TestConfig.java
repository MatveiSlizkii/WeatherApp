package com.senla.weather.config;

import com.senla.weather.services.ParseService;
import com.senla.weather.services.WeatherService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    public ParseService parseService(){
        return new ParseService();
    }
}
