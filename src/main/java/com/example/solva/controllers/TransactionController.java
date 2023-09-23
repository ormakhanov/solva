package com.example.solva.controllers;


import com.example.solva.service.TransactionService;
import com.example.solva.dto.TransactionLimitDTO;
import com.example.solva.dto.SaveTransactionDTO;
import com.example.solva.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping(value = "/{account}")
    public List<TransactionDTO> getAllTransaction(@PathVariable String account) {
        return transactionService.getByAccount(account);
    }

    @PostMapping(value = "/create")
    public TransactionDTO create(@RequestBody SaveTransactionDTO  saveTransactionDTO) {
        return transactionService.create(saveTransactionDTO);
    }

    @GetMapping(value = "/limitExceeded/{account}")
    public List<TransactionLimitDTO> getAllExceededLimitTransactionsByAccount(@PathVariable String account) {
        return transactionService.getAllExceededLimitTransactionsByAccount(account);
    }
}
