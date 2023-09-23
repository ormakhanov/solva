package com.example.solva.service.impl;

import com.example.solva.enums.CategoryEnum;
import com.example.solva.enums.CurrencyShortnameEnum;
import com.example.solva.exception.ResourceNotFoundException;
import com.example.solva.exception.UserAlreadyExistsException;
import com.example.solva.models.CurrencyEntity;
import com.example.solva.models.LimitEntity;
import com.example.solva.service.LimitService;
import com.example.solva.repository.CurrencyRepository;
import com.example.solva.repository.LimitRepository;
import com.example.solva.dto.SaveLimitDTO;
import com.example.solva.dto.LimitDTO;
import com.example.solva.dto.UpdateLimitDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;
    private final CurrencyRepository currencyRepository;

    @Override
    public List<LimitDTO> getAll(String userAccount) {
        return limitRepository.findAllByUserAccountAndCategory(userAccount).stream().map(LimitEntity::toDTO).collect(Collectors.toList());
    }

    @Override
    public LimitDTO create(SaveLimitDTO initLimitDTO) throws Exception {
            if (limitRepository.findFirstByUserAccountAndCategoryOrderByLimitDatetimeDesc(initLimitDTO.getAccount(), initLimitDTO.getCategory()) == null) {
                return limitRepository.save(new LimitEntity(
                        initLimitDTO.getAccount(),
                        initLimitDTO.getCategory(),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss XXX", Locale.getDefault()).format(new Date()),
                        BigDecimal.valueOf(1000.0),
                        BigDecimal.valueOf(1000.0)
                )).toDTO();
            } else {
                throw new UserAlreadyExistsException("For user " + initLimitDTO.getAccount() + " category " + initLimitDTO.getCategory() + " limit already exist!");
            }
    }

    @Override
    @Transactional
    public LimitDTO update(UpdateLimitDTO updateLimitDTO) {
        LimitEntity limitEntity = limitRepository.findFirstByUserAccountAndCategoryOrderByLimitDatetimeDesc(updateLimitDTO.getAccount(), updateLimitDTO.getCategory());
        if (limitEntity != null) {
            return limitRepository.saveAndFlush(new LimitEntity(
                    limitEntity.getUserAccount(),
                    limitEntity.getCategory(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss XXX", Locale.getDefault()).format(new Date()),
                    BigDecimal.valueOf(updateLimitDTO.getAccountLimit()),
                    BigDecimal.valueOf(updateLimitDTO.getAccountLimit() - limitEntity.getLimitSum().doubleValue() + limitEntity.getLimitBalance().doubleValue())
            )).toDTO();
        } else {
            throw new ResourceNotFoundException("There is no such account " + updateLimitDTO.getAccount());
        }
    }

    @Override
    public boolean updateLimitBalance(String account, CategoryEnum category, Double sum, CurrencyShortnameEnum currencyShortname) {
        CurrencyEntity currentCurrency = currencyRepository.getReferenceById(String.valueOf(currencyShortname));
        LimitEntity limitEntity = limitRepository.findFirstByUserAccountAndCategoryOrderByLimitDatetimeDesc(account, category);
        if (limitEntity != null) {
            limitEntity.setLimitBalance(BigDecimal.valueOf(limitEntity.getLimitBalance().doubleValue() - (sum / currentCurrency.getClose())));
            return limitRepository.saveAndFlush(limitEntity).getLimitBalance().doubleValue() < 0;
        } else {
            throw new ResourceNotFoundException("There is no such account " + account);
        }
    }


    public void exForTest() {
        throw new RuntimeException();
    }

}
