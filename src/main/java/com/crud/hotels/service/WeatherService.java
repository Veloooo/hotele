package com.crud.hotels.service;

import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Getter
@Setter
@ConfigurationProperties(prefix="weather.service")
public class WeatherService {

    @Autowired
    RequestsExecutorService executorService;

    private String apiKey;
    private String apiHost;
    private String apiForecastUrl;


    public String forecastFor30Days(String city){
        Request request = new Request.Builder()
                .url("https://community-open-weather-map.p.rapidapi.com/climate/month?q=San%20Francisco")
                .get()
                .addHeader("x-rapidapi-key", "06694d39e1mshd44c23226bb4bd3p1c42b0jsnd2a23b9b94c8")
                .addHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .build();

        String response = null;
        try {
            response = executorService.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


}
