package com.senla.weather.dao.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class yyyyMMddHHmmToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String str) {
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDate ld = LocalDate.parse(str, DATEFORMATTER);
        return LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());
    }
}
