package com.example.solva.service;

import com.example.solva.dto.TransactionLimitDTO;
import com.example.solva.dto.SaveTransactionDTO;
import com.example.solva.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionLimitDTO> getAllExceededLimitTransactionsByAccount(String account);

    List<TransactionDTO> getByAccount(String account);

    TransactionDTO create(SaveTransactionDTO saveTransactionDTO);
}
