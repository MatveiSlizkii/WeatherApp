package com.senla.weather.controller.web.controllers.rest;

import com.google.gson.*;
import com.senla.weather.model.dto.Weather;
import com.senla.weather.services.api.IWeatherService;
import com.senla.weather.services.exception.ValidationError;
import com.senla.weather.services.exception.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class WeatherController {

    private final IWeatherService weatherService;
    private final GsonBuilder gsonBuilder;

    public WeatherController(IWeatherService weatherService, GsonBuilder gsonBuilder) {
        this.weatherService = weatherService;
        this.gsonBuilder = gsonBuilder;
    }

    @Value("${location_name}")
    private String location;

    /**
     * Отдает информацию о последней записи о погоде в БД
     * @return
     */
    @GetMapping(value = {"/info"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Weather index() {
        return this.weatherService.get(location);
    }

    /**
     * Отдает информацию о средних показателях погоды
     * @param from с какой даты
     * @param to по какую дату
     * @return Json с префиксом "average_" объект Weather
     */
    @GetMapping(value = {"/avr"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String index(@RequestParam String from,
                        @RequestParam String to) {

        //Проверка на валидность входных параментров from и to
        List<ValidationError> errors = new ArrayList<>();
        ValidateFromTO(errors, to);
        ValidateFromTO(errors, from);
        if (!errors.isEmpty()) {
            throw new ValidationException("Переданы некорректные параметры", errors);
        }
        //конвертирование входных параметров from и to
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ldFrom = LocalDate.parse(from, DATEFORMATTER);
        LocalDate ldTo = LocalDate.parse(to, DATEFORMATTER);
        LocalDateTime from1 = LocalDateTime.of(ldFrom, LocalDateTime.now().toLocalTime());
        LocalDateTime to1 = LocalDateTime.of(ldTo, LocalDateTime.now().toLocalTime());

        Weather weather = weatherService.getAverage(location, from1, to1);

        //преобразование Json

        CustomFieldNamingStrategy namingStrategy = new CustomFieldNamingStrategy();
        Gson gson = gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT).setFieldNamingStrategy(namingStrategy).create();
        String result = gson.toJson(weather);
        return result;


    }

    /**
     * служит для представления в формате Json с префиксом "average_"
     */
    private static class CustomFieldNamingStrategy implements FieldNamingStrategy {
        @Override
        public String translateName(Field field) {
            return "average_" + field.getName();
        }
    }

    /**
     *
     * @param errors лист с ошибками выполнения запросв
     * @param fromOrTo параметр from или to пришедший с пользователького запроса
     * @return errors с потенциальными новыми ошибками
     */
    private static void ValidateFromTO (List<ValidationError> errors,String fromOrTo){
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate.parse(fromOrTo, DATEFORMATTER);
        } catch (DateTimeException e) {
            errors.add(new ValidationError(fromOrTo, "Переданный формат данных " +
                    "не соответствует виду \"dd-MM-yyyy\""));
        }
    }
}
