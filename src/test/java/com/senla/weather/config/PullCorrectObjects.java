package com.senla.weather.config;

import com.senla.weather.dao.entity.WeatherEntity;
import com.senla.weather.model.dto.Weather;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class PullCorrectObjects {
    private String correctJSON = "{\n" +
            "  \"location\": {\n" +
            "    \"name\": \"Minsk\",\n" +
            "    \"region\": \"Minsk\",\n" +
            "    \"country\": \"Belarus\",\n" +
            "    \"lat\": 53.9,\n" +
            "    \"lon\": 27.57,\n" +
            "    \"tz_id\": \"Europe/Minsk\",\n" +
            "    \"localtime_epoch\": 1679359552,\n" +
            "    \"localtime\": \"2023-03-21 3:45\"\n" +
            "  },\n" +
            "  \"current\": {\n" +
            "    \"last_updated_epoch\": 1679359500,\n" +
            "    \"last_updated\": \"2023-03-21 03:45\",\n" +
            "    \"temp_c\": 3,\n" +
            "    \"temp_f\": 37.4,\n" +
            "    \"is_day\": 0,\n" +
            "    \"condition\": {\n" +
            "      \"text\": \"Clear\",\n" +
            "      \"icon\": \"//cdn.weatherapi.com/weather/64x64/night/113.png\",\n" +
            "      \"code\": 1000\n" +
            "    },\n" +
            "    \"wind_mph\": 4.3,\n" +
            "    \"wind_kph\": 6.8,\n" +
            "    \"wind_degree\": 200,\n" +
            "    \"wind_dir\": \"SSW\",\n" +
            "    \"pressure_mb\": 1011,\n" +
            "    \"pressure_in\": 29.85,\n" +
            "    \"precip_mm\": 0,\n" +
            "    \"precip_in\": 0,\n" +
            "    \"humidity\": 87,\n" +
            "    \"cloud\": 0,\n" +
            "    \"feelslike_c\": 0.1,\n" +
            "    \"feelslike_f\": 32.2,\n" +
            "    \"vis_km\": 10,\n" +
            "    \"vis_miles\": 6,\n" +
            "    \"uv\": 1,\n" +
            "    \"gust_mph\": 14.1,\n" +
            "    \"gust_kph\": 22.7\n" +
            "  }\n" +
            "}";
    private Weather correctWeather = new Weather.Builder()
            .setTemperatureC(3.0)
            .setWindMph(4.3)
            .setPressureIn(29.85)
            .setHumidity(87)
            .setCondition("Clear")
            .setLocation("Minsk")
            .build();
    private Weather incorrectWeather = new Weather.Builder()
            .setTemperatureC(null)
            .setWindMph(4.3)
            .setPressureIn(null)
            .setHumidity(87)
            .setCondition("Clear")
            .setLocation("Minsk")
            .build();
    private Weather allNullIncorrectWeather = new Weather.Builder()
            .setTemperatureC(null)
            .setWindMph(null)
            .setPressureIn(null)
            .setHumidity(null)
            .setCondition(null)
            .setLocation(null)
            .build();
    private List<Weather> weatherListNull = new ArrayList<>();
    private WeatherEntity fullCorrectWeatherEntity = WeatherEntity.Builder.createBuilder()
            .setUuid(UUID.randomUUID())
            .setTemperatureC(3.0)
            .setWindMph(4.3)
            .setPressureIn(29.85)
            .setHumidity(87)
            .setCondition("Clear")
            .setLocation("Minsk")
            .setDtCreate(LocalDateTime.now())
            .build();
    private List<WeatherEntity> weatherListCorrect = new ArrayList<>();
    private WeatherEntity weather1 = WeatherEntity.Builder.createBuilder()
            .setTemperatureC(2.0)
            .setWindMph(2.0)
            .setPressureIn(2.0)
            .setHumidity(2)
            .setCondition("Clear")
            .setLocation("Minsk")
            .build();
    private WeatherEntity weather2 = WeatherEntity.Builder.createBuilder()
            .setTemperatureC(4.0)
            .setWindMph(4.0)
            .setPressureIn(4.0)
            .setHumidity(4)
            .setCondition("Clear")
            .setLocation("Minsk")
            .build();
    private WeatherEntity weather3 = WeatherEntity.Builder.createBuilder()
            .setTemperatureC(12.0)
            .setWindMph(12.0)
            .setPressureIn(12.0)
            .setHumidity(12)
            .setCondition("Sunny")
            .setLocation("Minsk")
            .build();
    private Weather avrWeather = Weather.Builder.createBuilder()
            .setTemperatureC(6.0)
            .setWindMph(6.0)
            .setPressureIn(6.0)
            .setHumidity(6)
            .setCondition("Clear")
            .setLocation("Minsk")
            .build();

    public PullCorrectObjects() {
    }

    public String getCorrectJSON() {
        return correctJSON;
    }

    public Weather getCorrectWeather() {
        return correctWeather;
    }

    public Weather getIncorrectWeather() {
        return incorrectWeather;
    }

    public WeatherEntity getFullCorrectWeatherEntity() {
        return fullCorrectWeatherEntity;
    }

    public List<WeatherEntity> getWeatherListCorrect() {
        weatherListCorrect.add(weather1);
        weatherListCorrect.add(weather2);
        weatherListCorrect.add(weather3);
        return weatherListCorrect;
    }

    public Weather getAllNullIncorrectWeather() {
        return allNullIncorrectWeather;
    }

    public Weather getAvrWeather() {
        return avrWeather;
    }

    public List<Weather> getWeatherListNull() {
        weatherListNull.add(allNullIncorrectWeather);
        weatherListNull.add(allNullIncorrectWeather);
        weatherListNull.add(allNullIncorrectWeather);

        return weatherListNull;
    }
}
