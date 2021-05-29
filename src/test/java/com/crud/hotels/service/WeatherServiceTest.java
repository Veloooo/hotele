package com.crud.hotels.service;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @MockBean
    private RequestsExecutorService executorService;


    @Test
    @Ignore
    public void propertiesShouldBeLoaded(){
        assertThat(weatherService.getApiForecastUrl()).isNotBlank();
        assertThat(weatherService.getApiHost()).isNotBlank();
        assertThat(weatherService.getApiKey()).isNotBlank();
    }

    @Test
    public void shouldReturnAnything(){
        assertThat(weatherService.forecastFor30Days("London")).isNotBlank();
    }

}
