FROM swaggerapi/swagger-ui:latest
MAINTAINER Matty

ADD /weather-service/spec/weather-service.yaml /usr/share/nginx/html/doc/weather-service.yaml


ENV URLS="[{ url: 'doc/weather-service.yaml', name: 'Weather'},]"
ENV URLS_PRIMARY_NAME="Weather"