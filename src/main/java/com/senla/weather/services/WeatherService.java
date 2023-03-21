package com.senla.weather.services;

import com.senla.weather.dao.api.IWeatherStorage;
import com.senla.weather.dao.entity.WeatherEntity;
import com.senla.weather.model.dto.Weather;
import com.senla.weather.services.api.IParseService;
import com.senla.weather.services.api.IWeatherService;
import com.senla.weather.services.exception.ValidationError;
import com.senla.weather.services.exception.ValidationException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
public class WeatherService implements IWeatherService {
    private final IWeatherStorage weatherStorage;
    private final ConversionService conversionService;
    private final IParseService parseService;

    public WeatherService(IWeatherStorage weatherStorage, ConversionService conversionService,
                          IParseService parseService) {
        this.weatherStorage = weatherStorage;
        this.conversionService = conversionService;
        this.parseService = parseService;
    }

    @Override
    public Weather save(Weather weather) {
        List<ValidationError> errors = new ArrayList<>();
        validateWeather(weather,errors);
        if (!errors.isEmpty()) {
            throw new ValidationException("Переданы некорректные параметры", errors);
        }
        weather.setUuid(UUID.randomUUID());
        weather.setDtCreate(LocalDateTime.now());
        weatherStorage.save(conversionService.convert(weather, WeatherEntity.class));
        return weather;
    }

    @Override
    public Weather get(String location) {
        if (!this.weatherStorage.existsWeatherEntityByLocation(location)){
            throw new ValidationException("Информация о погоде в данном регионе не найдена");
        }
        WeatherEntity weatherEntity = weatherStorage.findTop1ByLocationOrderByDtCreateDesc(location);
        Weather weather = conversionService.convert(weatherEntity, Weather.class);
        return weather;
    }

    @Override
    public Weather getAverage(String location, LocalDateTime from, LocalDateTime to) {

        if (!this.weatherStorage.existsWeatherEntityByLocationAndDtCreateBetween(location, from, to)){
            throw new ValidationException("Данных по запрашиваемому периоду не найдено");
        }

        List <WeatherEntity> weatherEntities = weatherStorage.findByLocationAndDtCreateBetween(location,from, to);
        double scale = Math.pow(10, 2);
        double avrTempC = Math.ceil(weatherEntities.stream().mapToDouble(o -> o.getTemperatureC()).average()
                            .orElseThrow(
                                    () -> new ValidationException("Не удалось подсчитать avrTempC"))* scale) / scale;
        double avrWindMph = Math.ceil(weatherEntities.stream().mapToDouble(o -> o.getWindMph()).average()
                            .orElseThrow(
                                    () -> new ValidationException("Не удалось подсчитать avrWindMph"))* scale) / scale;
        double avrPressureIn = Math.ceil(weatherEntities.stream().mapToDouble(o-> o.getPressureIn()).average()
                            .orElseThrow(
                                    () -> new ValidationException("Не удалось подсчитать avrPressureIn"))* scale) / scale;
        double avrHumidity = Math.ceil(weatherEntities.stream().mapToInt(o -> o.getHumidity()).average()
                            .orElseThrow(
                                    () -> new ValidationException("Не удалось подсчитать avrHumidity"))* scale) / scale;
        String avrCondition = weatherEntities.
                stream().map(o-> o.getCondition()).collect(Collectors.toList()).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
                .get().getKey();

        Weather weather = Weather.Builder.createBuilder()
                .setTemperatureC(avrTempC)
                .setWindMph(avrWindMph)
                .setPressureIn(avrPressureIn)
                .setHumidity((int)avrHumidity)
                .setCondition(avrCondition)
                .setLocation(location)
                .build();

        return weather;
    }

    @Override
    public Weather saveFromAPI(String location) {
        Weather weather = parseService.getWeatherFromAPI(location);
        return this.save(weather);
    }

    @Override
    public boolean validateWeather(Weather weather, List<ValidationError> errors) {
        String messageError = "Переданы некорректные параметры";
        if (weather == null){
            errors.add(new ValidationError("weather", messageError));
        }

        if (weather.getTemperatureC() == null){
            errors.add(new ValidationError("TemperatureC", messageError));

        }
        if (weather.getWindMph() == null){
            errors.add(new ValidationError("WindMph", messageError));

        }
        if (weather.getPressureIn() == null){
            errors.add(new ValidationError("PressureIn", messageError));

        }
        if (weather.getHumidity() == null){
            errors.add(new ValidationError("Humidity", messageError));

        }
        if (weather.getCondition() == null){
            errors.add(new ValidationError("Condition", messageError));

        }
        if (weather.getLocation() == null){
            errors.add(new ValidationError("Location", messageError));

        }
        return true;
    }
}
