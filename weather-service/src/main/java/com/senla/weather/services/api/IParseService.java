package com.senla.weather.services.api;

import com.senla.weather.model.dto.Weather;

public interface IParseService {
    /**
     * Парсит информацию с API, описанную внутри метода
     * @param location (указывыается в application.properties)
     * @return Json в виде строки
     */
    String parseAPI (String location);

    /**
     * Преобразовывает Json в виде строки в объект Weather
     * @param myJSON Json в виде строки
     * @return Объект Weather
     */
    Weather convertStringToObject (String myJSON);

    /**
     * Описывает логику взаимодействия полного цикла от подключения к API до получения Объекта Weather
     * @param location (указывыается в application.properties)
     * @return Объект Weather
     */
    Weather getWeatherFromAPI(String location);

}
