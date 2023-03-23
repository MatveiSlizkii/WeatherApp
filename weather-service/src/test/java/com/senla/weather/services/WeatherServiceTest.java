package com.senla.weather.services;

import com.senla.weather.config.PullCorrectObjects;
import com.senla.weather.dao.api.IWeatherStorage;
import com.senla.weather.dao.converters.WeatherConverter;
import com.senla.weather.dao.entity.WeatherEntity;
import com.senla.weather.model.dto.Weather;
import com.senla.weather.services.exception.ValidationError;
import com.senla.weather.services.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock(strictness = Mock.Strictness.LENIENT)
    private IWeatherStorage weatherStorage;
    @Mock(strictness = Mock.Strictness.LENIENT)
    private ConversionService conversionService;
    @Mock
    private WeatherConverter weatherConverter;
    @InjectMocks
    private WeatherService weatherService;

    PullCorrectObjects pullCorrectObjects = new PullCorrectObjects();
    private final Weather incorrectWeather = pullCorrectObjects.getIncorrectWeather();
    private final Weather correctWeather = pullCorrectObjects.getCorrectWeather();
    private final WeatherEntity fullCorrectWeather = pullCorrectObjects.getFullCorrectWeatherEntity();
    private final Weather fullNullWeather = pullCorrectObjects.getAllNullIncorrectWeather();
    private final List<WeatherEntity> correctWeatherList = pullCorrectObjects.getWeatherListCorrect();
    private final String location = "Minsk";

    /**
     * Если невалидный объект Weather
     */
    @Test
    void saveFalseWithIncorrectWeather() {
        Assertions.assertThrows(ValidationException.class, () -> {
            weatherService.save(incorrectWeather);
        });
    }

    /**
     * Если валидный объект Weather
     */
    @Test
    void saveTrueWithCorrectWeather() {
        Mockito.when(weatherStorage.save(conversionService.convert(Mockito.mock(Weather.class), WeatherEntity.class)))
                .thenReturn(fullCorrectWeather);
        Assertions.assertInstanceOf(Weather.class, weatherService.save(correctWeather));
        Assertions.assertNotNull(correctWeather.getUuid());
        Assertions.assertNotNull(correctWeather.getDtCreate());
    }

    /**
     * Если в бд нет записи о погоде
     */
    @Test
    void getErrorExist() {
        Mockito.when(weatherStorage.existsWeatherEntityByLocation(location)).thenReturn(false);

        Assertions.assertThrows(ValidationException.class, () -> {
            weatherService.get(location);
        });

        Mockito.verify(weatherStorage,Mockito.times(1)).existsWeatherEntityByLocation(location);
    }

    /**
     * Успешное выполнение при валидных данных и наличии в БД сущности
     */
    @Test
    void getAccept() {
        Mockito.when(weatherStorage.existsWeatherEntityByLocation(location)).thenReturn(true);
        Mockito.when(weatherStorage.findTop1ByLocationOrderByDtCreateDesc(location)).thenReturn(fullCorrectWeather);
        Mockito.when(conversionService.convert(fullCorrectWeather, Weather.class)).thenReturn(correctWeather);

        Assertions.assertInstanceOf(Weather.class, weatherService.get(location));

        Mockito.verify(weatherStorage,Mockito.times(1))
                .existsWeatherEntityByLocation(location);
        Mockito.verify(weatherStorage,Mockito.times(1))
                .findTop1ByLocationOrderByDtCreateDesc(location);

    }

    /**
     * Проверка правильного подсчета средних значений объекта Weather
     */
    @Test
    void getAverageCorrect() {
        LocalDateTime ldt = LocalDateTime.now();
        Mockito.when(weatherStorage.existsWeatherEntityByLocationAndDtCreateBetween(location,
                ldt, ldt)).thenReturn(true);
        Mockito.when(weatherStorage.findByLocationAndDtCreateBetween(location,
                ldt,ldt)).thenReturn(correctWeatherList);
        Weather weather = weatherService.getAverage(location, ldt, ldt);

        Mockito.verify(weatherStorage,Mockito.times(1))
                .existsWeatherEntityByLocationAndDtCreateBetween(location, ldt, ldt);
        Mockito.verify(weatherStorage,Mockito.times(1))
                .findByLocationAndDtCreateBetween(location, ldt,ldt);

        Assertions.assertEquals(6.0, weather.getTemperatureC());
        Assertions.assertEquals(6.0, weather.getPressureIn());
        Assertions.assertEquals(6, weather.getHumidity());
        Assertions.assertEquals("Clear", weather.getCondition());
        Assertions.assertEquals("Minsk", weather.getLocation());


    }

    /**
     * На наличие ошибки если за указанный период не было записей в БД
     */
    @Test
    void getAverageException() {
        LocalDateTime ldt = LocalDateTime.now();
        Mockito.when(weatherStorage.existsWeatherEntityByLocationAndDtCreateBetween(location,
                                    ldt, ldt)).thenReturn(false);
        Assertions.assertThrows(ValidationException.class, () -> {
            weatherService.getAverage(location, ldt, ldt).getHumidity();
        });

        Mockito.verify(weatherStorage,Mockito.times(1))
                .existsWeatherEntityByLocationAndDtCreateBetween(location, ldt,ldt);
    }

    /**
     * Проверка валидатора Weather при валидном запросе
     */
    @Test
    void validateWeatherTrue() {
        List<ValidationError> errors = new ArrayList<>();
        Assertions.assertTrue(weatherService.validateWeather(correctWeather, errors));
    }
    /**
     * Проверка валидатора Weather при невалидном запросе
     */
    @Test
    void validateWeatherFalse() {
        List<ValidationError> errors = new ArrayList<>();
        weatherService.validateWeather(fullNullWeather, errors);
        Assertions.assertEquals(errors.size(), 6);
    }
}