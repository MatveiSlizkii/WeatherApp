package com.senla.weather.dao.converters;

import com.senla.weather.dao.entity.WeatherEntity;
import com.senla.weather.model.dto.Weather;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class WeatherConverter implements Converter<WeatherEntity, Weather> {
    @Override
    public Weather convert(WeatherEntity source) {

        return Weather.Builder.createBuilder()
                .setUuid(source.getUuid())
                .setTemperatureC(source.getTemperatureC())
                .setWindMph(source.getWindMph())
                .setPressureIn(source.getPressureIn())
                .setHumidity(source.getHumidity())
                .setCondition(source.getCondition())
                .setLocation(source.getLocation())
                .setDtCreate(source.getDtCreate())
                .build();
    }

    @Override
    public <U> Converter<WeatherEntity, U> andThen(Converter<? super Weather, ? extends U> after) {
        return Converter.super.andThen(after);
    }


}
