package com.example.solva.dto;


import com.example.solva.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LimitDTO {
    private String userAccount;
    private String limitCurrencyShortname;
    private Double limitSum;
    private Double limitBalance;
    private CategoryEnum category;
    private String limitDatetime;
}
