CREATE DATABASE weather_service WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Belarusian_Belarus.1251';

ALTER DATABASE weather_service OWNER TO postgres;

\connect weather_service

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

CREATE SCHEMA app;

ALTER SCHEMA app OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

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

ALTER TABLE app.weather_info OWNER TO postgres;

ALTER TABLE ONLY app.weather_info
    ADD CONSTRAINT info_weather_pkey PRIMARY KEY (uuid);

