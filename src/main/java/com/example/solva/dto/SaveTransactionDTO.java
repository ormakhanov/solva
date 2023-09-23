package com.example.solva.dto;


import com.example.solva.enums.CategoryEnum;
import com.example.solva.enums.CurrencyShortnameEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveTransactionDTO {
    private String accountFrom;
    private String accountTo;
    private CurrencyShortnameEnum currencyShortname;
    private Double sum;
    private CategoryEnum category;
}
