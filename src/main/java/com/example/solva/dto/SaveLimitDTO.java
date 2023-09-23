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
public class SaveLimitDTO {
    private String account;
    private CategoryEnum category;
}
