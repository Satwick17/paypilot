package com.paypilot.merchant.controller;

import com.paypilot.merchant.dto.CreateMerchantRequest;
import com.paypilot.merchant.entity.Merchant;
import com.paypilot.merchant.service.MerchantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Merchant createMerchant(
            @Valid @RequestBody CreateMerchantRequest request
    ) {
        return merchantService.createMerchant(
                request.merchantCode(),
                request.name(),
                request.email()
        );
    }

    @GetMapping
    public List<Merchant> getAllMerchants() {
        return merchantService.getAllMerchants();
    }
}