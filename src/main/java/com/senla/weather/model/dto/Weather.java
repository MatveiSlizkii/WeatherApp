package com.senla.weather.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.UUID;

public class Weather {
    @JsonIgnore
    private UUID uuid;
    private Double temperatureC;
    private Double windMph;
    private Double pressureIn;
    private Integer humidity;
    private String condition;
    private String location;
    @JsonIgnore
    private transient LocalDateTime dtCreate;

    public Weather() {
    }

    public Weather(UUID uuid, Double temperatureC,
                   Double windMph, Double pressureIn,
                   Integer humidity, String condition,
                   String location, LocalDateTime dtCreate) {
        this.uuid = uuid;
        this.temperatureC = temperatureC;
        this.windMph = windMph;
        this.pressureIn = pressureIn;
        this.humidity = humidity;
        this.condition = condition;
        this.location = location;
        this.dtCreate = dtCreate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Double getTemperatureC() {
        return temperatureC;
    }

    public void setTemperatureC(Double temperatureC) {
        this.temperatureC = temperatureC;
    }

    public Double getWindMph() {
        return windMph;
    }

    public void setWindMph(Double windMph) {
        this.windMph = windMph;
    }

    public Double getPressureIn() {
        return pressureIn;
    }

    public void setPressureIn(Double pressureIn) {
        this.pressureIn = pressureIn;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "uuid=" + uuid +
                ", temperatureC=" + temperatureC +
                ", windMph=" + windMph +
                ", pressureIn=" + pressureIn +
                ", humidity=" + humidity +
                ", condition='" + condition + '\'' +
                ", location='" + location + '\'' +
                ", dtCreate=" + dtCreate +
                '}';
    }

    public static class Builder {
        private UUID uuid;
        private Double temperatureC;
        private Double windMph;
        private Double pressureIn;
        private Integer humidity;
        private String condition;
        private String location;
        private LocalDateTime dtCreate;


        public Builder() {

        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setTemperatureC(Double temperatureC) {
            this.temperatureC = temperatureC;
            return this;
        }

        public Builder setWindMph(Double windMph) {
            this.windMph = windMph;
            return this;
        }

        public Builder setPressureIn(Double pressureIn) {
            this.pressureIn = pressureIn;
            return this;
        }

        public Builder setHumidity(Integer humidity) {
            this.humidity = humidity;
            return this;
        }

        public Builder setCondition(String condition) {
            this.condition = condition;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Weather build() {
            return new Weather(uuid, temperatureC, windMph, pressureIn,
                    humidity, condition, location,  dtCreate);
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        return false;
    }


    @Override
    public int hashCode() {
        return 7;
    }
}
