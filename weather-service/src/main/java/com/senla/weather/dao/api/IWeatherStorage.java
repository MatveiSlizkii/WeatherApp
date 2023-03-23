package com.senla.weather.dao.api;

import com.senla.weather.dao.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface IWeatherStorage extends JpaRepository<WeatherEntity, UUID> {
        WeatherEntity findTop1ByLocationOrderByDtCreateDesc(String location);
        List<WeatherEntity> findByLocationAndDtCreateBetween(String location, LocalDateTime from, LocalDateTime to);
        //Для проверок
        boolean existsWeatherEntityByLocation (String location);
        boolean existsWeatherEntityByLocationAndDtCreateBetween(String location, LocalDateTime from, LocalDateTime to);


}
