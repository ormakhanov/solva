package com.example.solva.service.impl;

import com.example.solva.models.TransactionEntity;
import com.example.solva.service.TransactionService;
import com.example.solva.repository.LimitRepository;
import com.example.solva.repository.TransactionRepository;
import com.example.solva.dto.SaveTransactionDTO;
import com.example.solva.dto.TransactionLimitDTO;
import com.example.solva.dto.TransactionDTO;
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
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final LimitRepository limitRepository;
    private final LimitServiceImpl limitService;

    @Override
    public List<TransactionLimitDTO> getAllExceededLimitTransactionsByAccount(String account) {
        return transactionRepository.findAllByAccountFromJoinLimits(account);
    }

    @Override
    public List<TransactionDTO> getByAccount(String account) {
        return transactionRepository.findAllByAccountFrom(account).stream().map(TransactionEntity::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TransactionDTO create(SaveTransactionDTO saveTransactionDTO) {
        return transactionRepository.saveAndFlush(new TransactionEntity(
                saveTransactionDTO.getAccountFrom(),
                saveTransactionDTO.getAccountTo(),
                saveTransactionDTO.getCurrencyShortname(),
                BigDecimal.valueOf(saveTransactionDTO.getSum()),
                saveTransactionDTO.getCategory(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss XXX", Locale.getDefault()).format(new Date()),
                limitService.updateLimitBalance(saveTransactionDTO.getAccountFrom(), saveTransactionDTO.getCategory(), saveTransactionDTO.getSum(), saveTransactionDTO.getCurrencyShortname()),
                limitRepository.findFirstByUserAccountAndCategoryOrderByLimitDatetimeDesc(saveTransactionDTO.getAccountFrom(), saveTransactionDTO.getCategory())
        )).toDTO();
    }
}
