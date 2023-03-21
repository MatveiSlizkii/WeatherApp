package com.senla.weather;

import com.senla.weather.model.dto.Weather;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class Test {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        headers.set("X-RapidAPI-Key", "2b89890622msha86bcd9f019cdd3p1d0ae4jsn7ab337b92a23");
        headers.set("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com");
        ResponseEntity<String> response3 =
                restTemplate.exchange(
                        "https://weatherapi-com.p.rapidapi.com/current.json?q=paris", HttpMethod.GET, entity,
                        String.class);
        String raw = response3.getBody();
        JSONObject myObject = new JSONObject(raw);
        System.out.println(myObject);
        System.out.println(myObject.getJSONObject("current").getInt("feelslike_c"));
        Weather weather = new Weather.Builder()
                .setTemperatureC(myObject.getJSONObject("current").getDouble("temp_c"))
                .setWindMph(myObject.getJSONObject("current").getDouble("wind_mph"))
                .setPressureIn(myObject.getJSONObject("current").getDouble("pressure_in"))
                .setHumidity(myObject.getJSONObject("current").getInt("humidity"))
                .setCondition(myObject.getJSONObject("current").getJSONObject("condition").getString("text"))
                .setLocation(myObject.getJSONObject("location").getString("name"))
                .build();
        System.out.println(weather);
    }
}
