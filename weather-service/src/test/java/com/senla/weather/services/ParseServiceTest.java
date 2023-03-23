package com.senla.weather.services;

import com.senla.weather.config.PullCorrectObjects;
import com.senla.weather.config.TestConfig;
import com.senla.weather.model.dto.Weather;
import com.senla.weather.services.exception.ValidationException;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class ParseServiceTest {

    //mocked
    @Autowired
    private ParseService parseService;



    private static final String myJson = null;
    private static final String myJson1 = "sdsds";
    PullCorrectObjects pullCorrectObjects = new PullCorrectObjects();
    private final String correctJSON =  pullCorrectObjects.getCorrectJSON();



    @BeforeAll
    static void beforeAll() {

    }

    /**
     * Проверка на ошибку если muJsom == null
     */
    @Test
    void convertStringToObjectShouldRejectWithNullJson() {
        Assertions.assertThrows(ValidationException.class, () -> {
            parseService.convertStringToObject(myJson);
        });
    }

    /**
     * Если невалидный Json
     */
    @Test
    void convertStringToObjectShouldRejectWithIncorrectJson() {
        Assertions.assertThrows(JSONException.class, () -> {
            parseService.convertStringToObject(myJson1);
        });
    }
    /**
     * Если правильный Json
     */
    @Test
    void convertStringToObjectShouldAcceptWithCorrectJson() {
        Assertions.assertAll(() -> {
            parseService.convertStringToObject(correctJSON);
        });
        Weather weather = parseService.convertStringToObject(correctJSON);
        Assertions.assertInstanceOf(Weather.class, parseService.convertStringToObject(correctJSON));
        Assertions.assertEquals(3.0, weather.getTemperatureC());
        Assertions.assertEquals(4.3, weather.getWindMph());
        Assertions.assertEquals(29.85, weather.getPressureIn());
        Assertions.assertEquals(87, weather.getHumidity());
        Assertions.assertEquals("Clear", weather.getCondition());
        Assertions.assertEquals("Minsk", weather.getLocation());
    }

}
