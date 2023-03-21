package com.senla.weather.services;

import com.senla.weather.model.dto.Weather;
import com.senla.weather.services.api.IParseService;
import com.senla.weather.services.exception.ValidationException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ParseService implements IParseService {
    private final RestTemplate restTemplate;

    public ParseService() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public String parseAPI(String location) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        headers.set("X-RapidAPI-Key", "2b89890622msha86bcd9f019cdd3p1d0ae4jsn7ab337b92a23");
        headers.set("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com");

        try {
            ResponseEntity<String> response =
                    restTemplate.exchange(
                            "https://weatherapi-com.p.rapidapi.com/current.json?q=" + location, HttpMethod.GET, entity,
                            String.class);
            return response.getBody();
        } catch (HttpClientErrorException e){
            throw new ValidationException("Неверное подключение к API");
        }
    }

    @Override
    public Weather convertStringToObject(String myJSON) {
        if (myJSON == null || myJSON.isEmpty()){
            throw new ValidationException("Передан пустой JSON");
        }
        JSONObject myObject = new JSONObject(myJSON);
        try {
            Weather weather = new Weather.Builder()
                    .setTemperatureC(myObject.getJSONObject("current").getDouble("temp_c"))
                    .setWindMph(myObject.getJSONObject("current").getDouble("wind_mph"))
                    .setPressureIn(myObject.getJSONObject("current").getDouble("pressure_in"))
                    .setHumidity(myObject.getJSONObject("current").getInt("humidity"))
                    .setCondition(myObject.getJSONObject("current").getJSONObject("condition").getString("text"))
                    .setLocation(myObject.getJSONObject("location").getString("name"))
                    .build();
            return weather;
        } catch (JSONException e){
            throw new ValidationException("Невозможно произвести парсинг полученного JSON");
        }

    }

    @Override
    public Weather getWeatherFromAPI(String location) {
        String rawJson = this.parseAPI(location);
        Weather weather = this.convertStringToObject(rawJson);
        return weather;
    }
}
