package com.senla.weather.services.api;

import com.senla.weather.model.dto.Weather;
import com.senla.weather.services.exception.ValidationError;

import java.time.LocalDateTime;
import java.util.List;

public interface IWeatherService {
    /**
     * Сохраняет объект в БД, присваивая внутри поля UUID и DtCreate
     * @param weather без значение UUID и DtCreate
     * @return загруженный объект с информации UUID и DtCreate
     */
    Weather save (Weather weather);

    /**
     * Получает объект с БД
     * @param location (указывается в application.properties)
     * @return объект типа Weather, полученный с БД
     */
    Weather get (String location);

    /**
     * Получает c БД список объектов Weather, которые были загружены в БД в период с @param from по @param to,
     * на базе списка подсчитает средние показатели погоды
     * @param location (указывается в application.properties)
     * @param from с какой даты
     * @param to по какую дату
     * @return объект Weather со средними показателями погоды
     */
    Weather getAverage(String location, LocalDateTime from, LocalDateTime to);

    /**
     * Сохраняет в БД информацию полученную в API
     * @param location (указывается в application.properties)
     * @return
     */
    Weather saveFromAPI(String location);

    /**
     * Проверяет на целостность объект Weather
     * @param weather
     * @return true/false
     */
    boolean validateWeather (Weather weather, List<ValidationError> errors);

}
