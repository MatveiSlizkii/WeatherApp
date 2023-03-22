CREATE USER "weather-service_user" WITH PASSWORD 'TiP22r1nJyyu';
CREATE DATABASE "weather-service" WITH OWNER = "weather-service_user";
\c "weather-service"

SET client_encoding = 'UTF8';

CREATE SCHEMA app;

ALTER SCHEMA app OWNER TO "weather-service_user";

SET default_tablespace = '';

CREATE TABLE app.weather_info (
    uuid uuid NOT NULL,
    temp_c double precision NOT NULL,
    wind_mph double precision NOT NULL,
    pressure_in double precision NOT NULL,
    humidity bigint NOT NULL,
    condition character varying NOT NULL,
    location character varying NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL
);

ALTER TABLE app.weather_info OWNER TO "weather-service_user";

ALTER TABLE ONLY app.weather_info
    ADD CONSTRAINT info_weather_pkey PRIMARY KEY (uuid);

