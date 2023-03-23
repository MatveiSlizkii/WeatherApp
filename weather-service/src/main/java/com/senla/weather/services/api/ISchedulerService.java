package com.senla.weather.services.api;

public interface ISchedulerService {
    /**
     * метод отвечает за получение данных о погоде с определяемым таймером подключения
     */
    void setWeatherFromAPI();
}
