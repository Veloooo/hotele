package com.crud.hotels.backend.service;

import com.crud.hotels.backend.weather.Items;
import com.crud.hotels.backend.weather.WeatherInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
@ConfigurationProperties(prefix = "weather.service")
public class WeatherService {

    OkHttpClient client = new OkHttpClient();
    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static final String HEADER_API_KEY = "x-rapidapi-key";
    public static final String HEADER_API_KEY_VALUE = "06694d39e1mshd44c23226bb4bd3p1c42b0jsnd2a23b9b94c8";
    public static final String HEADER_API_HOST = "x-rapidapi-host";
    public static final String HEADER_API_HOST_VALUE = "community-open-weather-map.p.rapidapi.com";
    public static final String API_FORECAST_URL = "https://community-open-weather-map.p.rapidapi.com/forecast?q=";


    public WeatherInfo forecastFor5Days(String city, String countryCode) {
        Request request = new Request.Builder()
                .url(API_FORECAST_URL + city + "," + countryCode)
                .get()
                .addHeader(HEADER_API_HOST, HEADER_API_HOST_VALUE)
                .addHeader(HEADER_API_KEY, HEADER_API_KEY_VALUE)
                .build();

        WeatherInfo info = null;
        try {
            info = execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(info).setList(info.getList().stream()
                .map(this::mapToCelcius)
                .collect(Collectors.toList()));
        return info;
    }

    private Items mapToCelcius(Items items) {
        items.getMain().setFeelsLike(items.getMain().getFeelsLike() - 273.1);
        items.getMain().setTemp(items.getMain().getTemp() - 273.1);
        items.getMain().setTempMax(items.getMain().getTempMax() - 273.1);
        items.getMain().setTempMin(items.getMain().getTempMin() - 273.1);
        return items;
    }

    public List<Items> getDataForCityForDay(String city, String countryCode, LocalDateTime dateTime) {
        WeatherInfo info = forecastFor5Days(city, countryCode);

        return info.getList().stream()
                .filter(w ->
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(w.getDt()),
                            TimeZone.getDefault().toZoneId()).getDayOfYear() == dateTime.getDayOfYear()
                )
                .collect(Collectors.toList());
    }


    public String getDataPrettyPrinted(WeatherInfo info) {
        String objectPrettyPrinted = null;
        try {
            objectPrettyPrinted = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(info);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return objectPrettyPrinted;
    }

    private WeatherInfo execute(Request request) throws IOException {
        return mapper.readValue(client.newCall(request).execute().body().string(), WeatherInfo.class);
    }

}
