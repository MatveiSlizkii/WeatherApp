package com.senla.weather.config;

import com.senla.weather.dao.converters.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new WeatherConverter());
        registry.addConverter(new WeatherEntityConverter());
        registry.addConverter(new LongToLocalDateTimeConverter());
        registry.addConverter(new yyyyMMddHHmmToLocalDateTimeConverter());
    }
}
