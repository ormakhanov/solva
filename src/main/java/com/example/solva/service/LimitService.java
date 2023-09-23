package com.example.solva.service;

import com.example.solva.dto.SaveLimitDTO;
import com.example.solva.dto.LimitDTO;
import com.example.solva.dto.UpdateLimitDTO;
import com.example.solva.enums.CategoryEnum;
import com.example.solva.enums.CurrencyShortnameEnum;

import java.util.List;

public interface LimitService {

    List<LimitDTO> getAll(String userAccount);

    LimitDTO create(SaveLimitDTO initLimitDTO) throws Exception;

    LimitDTO update(UpdateLimitDTO updateLimitDTO);

    boolean updateLimitBalance(String account, CategoryEnum category, Double sum, CurrencyShortnameEnum currencyShortname);
}
