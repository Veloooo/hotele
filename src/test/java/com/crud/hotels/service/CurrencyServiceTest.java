package com.crud.hotels.service;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

import java.util.Currency;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @InjectMocks
    private CurrencyService currencyService;


    @Test
    public void shouldReturnAnyhing(){
        double rate = currencyService.convertPriceToCurrency(Currency.getInstance("PLN"), Currency.getInstance("USD"));
        System.out.println(rate);
        assertThat(rate).isGreaterThan(0);
    }

    @Test
    public void shouldBeApprox3(){
        double dollarsAmount = currencyService.getValueInOtherCurrency(Currency.getInstance("PLN"), Currency.getInstance("USD"), 10);
        System.out.println(dollarsAmount);
        assertThat(dollarsAmount).isBetween(2.5, 3.5);
    }
}
