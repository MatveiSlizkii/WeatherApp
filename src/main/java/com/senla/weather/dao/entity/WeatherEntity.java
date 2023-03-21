package com.senla.weather.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "weather_info", schema = "app")
public class WeatherEntity {
    @Id
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "temp_c")
    private Double temperatureC;
    @Column(name = "wind_mph")
    private Double windMph;
    @Column(name = "pressure_in")
    private Double pressureIn;
    @Column(name = "humidity")
    private Integer humidity;
    @Column(name = "condition")
    private String condition;
    @Column(name = "location")
    private String location;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    public WeatherEntity() {
    }

    public WeatherEntity(UUID uuid, Double temperatureC,
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

    public double getTemperatureC() {
        return temperatureC;
    }

    public void setTemperatureC(double temperatureC) {
        this.temperatureC = temperatureC;
    }

    public double getWindMph() {
        return windMph;
    }

    public void setWindMph(double windMph) {
        this.windMph = windMph;
    }

    public double getPressureIn() {
        return pressureIn;
    }

    public void setPressureIn(double pressureIn) {
        this.pressureIn = pressureIn;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
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

    public static class Builder {
        private UUID uuid;
        private Double temperatureC;
        private Double windMph;
        private Double pressureIn;
        private Integer humidity;
        private String condition;
        private String location;
        private LocalDateTime dtCreate;


        private Builder() {

        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setTemperatureC(double temperatureC) {
            this.temperatureC = temperatureC;
            return this;
        }

        public Builder setWindMph(double windMph) {
            this.windMph = windMph;
            return this;
        }

        public Builder setPressureIn(double pressureIn) {
            this.pressureIn = pressureIn;
            return this;
        }

        public Builder setHumidity(int humidity) {
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

        public WeatherEntity build() {
            return new WeatherEntity(uuid, temperatureC, windMph, pressureIn,
                    humidity, condition, location, dtCreate);
        }

    }
}
