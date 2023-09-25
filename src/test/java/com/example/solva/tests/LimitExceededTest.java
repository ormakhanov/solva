package com.example.solva.tests;

import com.example.solva.enums.CategoryEnum;
import com.example.solva.enums.CurrencyShortnameEnum;
import com.example.solva.models.CurrencyEntity;
import com.example.solva.models.LimitEntity;
import com.example.solva.repository.CurrencyRepository;
import com.example.solva.repository.LimitRepository;
import com.example.solva.service.impl.LimitServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LimitExceededTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private LimitRepository limitRepository;

    @InjectMocks
    private LimitServiceImpl limitService;

    private static final String ACCOUNT = "Askar";
    private static final CategoryEnum CATEGORY = CategoryEnum.service;
    private static final Double SUM = 9000.0;
    private static final CurrencyShortnameEnum CURRENCY_SHORTNAME = CurrencyShortnameEnum.KZT;
    private static final Double currency_close = 450.0;
    private static final Double LimitBalance = 10.0;
    private static final Double ExpectedResult = -10.0;

    // LimitBalance - (SUM / currency_close) = ExpectedResult

    @Test
    public void testUpdateLimitBalanceWithValidData() {
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setCurrencyType(CURRENCY_SHORTNAME.toString());
        currencyEntity.setClose(currency_close);

        LimitEntity limitEntity = new LimitEntity();
        limitEntity.setLimitBalance(BigDecimal.valueOf(LimitBalance));

        when(currencyRepository.getReferenceById(any())).thenReturn(currencyEntity);
        when(limitRepository.findFirstByUserAccountAndCategoryOrderByLimitDatetimeDesc(ACCOUNT, CATEGORY)).thenReturn(limitEntity);
        when(limitRepository.saveAndFlush(limitEntity)).thenReturn(limitEntity);

        boolean limitExceeded = limitService.updateLimitBalance(ACCOUNT, CATEGORY, SUM, CURRENCY_SHORTNAME);

        assertTrue(limitExceeded);
        assertEquals(ExpectedResult, limitEntity.getLimitBalance().doubleValue(), 0.01);
        verify(limitRepository, times(1)).saveAndFlush(limitEntity);
    }
}
