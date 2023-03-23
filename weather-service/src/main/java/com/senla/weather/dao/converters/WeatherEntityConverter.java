package com.senla.weather.dao.converters;

import com.senla.weather.dao.entity.WeatherEntity;
import com.senla.weather.model.dto.Weather;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class WeatherEntityConverter implements Converter<Weather, WeatherEntity> {
    @Override
    public WeatherEntity convert(Weather source) {

        return WeatherEntity.Builder.createBuilder()
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
    public <U> Converter<Weather, U> andThen(Converter<? super WeatherEntity, ? extends U> after) {
        return Converter.super.andThen(after);
    }


}
