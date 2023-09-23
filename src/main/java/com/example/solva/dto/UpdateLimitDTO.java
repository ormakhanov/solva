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
public class UpdateLimitDTO {
    private String account;
    private CategoryEnum category;
    private Double accountLimit;
}
