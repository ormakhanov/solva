package com.example.solva.controllers;

import com.example.solva.service.impl.LimitServiceImpl;
import com.example.solva.dto.SaveLimitDTO;
import com.example.solva.dto.LimitDTO;
import com.example.solva.dto.UpdateLimitDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/limits")
@AllArgsConstructor
public class LimitController {

    private final LimitServiceImpl limitService;

    @GetMapping(value = "/{account}")
    public List<LimitDTO> getAccountLimits(@PathVariable String account) {
        return limitService.getAll(account);
    }

    @PutMapping(value = "/update")
    public LimitDTO update(@RequestBody UpdateLimitDTO updateLimitDTO) {
        return limitService.update(updateLimitDTO);
    }

    @PostMapping(value = "/create")
    public LimitDTO create(@RequestBody SaveLimitDTO initLimitDTO) throws Exception {
        return limitService.create(initLimitDTO);
    }
}
