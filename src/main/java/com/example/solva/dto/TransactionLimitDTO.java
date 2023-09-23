package com.example.solva.dto;

import com.example.solva.enums.CategoryEnum;
import com.example.solva.enums.CurrencyShortnameEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionLimitDTO {
    private Long id;
    private String accountFrom;
    private String accountTo;
    private CurrencyShortnameEnum currencyShortname;
    private BigDecimal sum;
    private CategoryEnum category;
    private String dateTime;
    private BigDecimal accountLimit;
    private BigDecimal exceededLimit;
    private String limitSettingDate;
    private boolean limitExceeded;

}
