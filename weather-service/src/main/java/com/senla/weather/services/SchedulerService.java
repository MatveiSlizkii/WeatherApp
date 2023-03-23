package com.senla.weather.services;

import com.senla.weather.services.api.ISchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;

@Service
@EnableScheduling
public class SchedulerService implements ISchedulerService {
    private final WeatherService weatherService;
    private final Logger logger = LoggerFactory.getLogger(SchedulerService.class);


    public SchedulerService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Value("${location_name}")
    private String location;

    @Scheduled(fixedRate = 3 * 30 * 1000)
    @Async
    @Override
    public void setWeatherFromAPI() {
        try {
            weatherService.saveFromAPI(location);
            this.logger.info("{}: Внесение данных о погоде в локации " + location +
                    " произведена успешно", LocalDateTime.now());
        } catch (ResourceAccessException e){
            this.logger.warn("Проблемы с соединением к API");
        }

    }
}
