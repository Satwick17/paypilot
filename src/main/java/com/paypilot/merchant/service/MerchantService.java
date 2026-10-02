package com.paypilot.merchant.service;

import com.paypilot.merchant.entity.Merchant;
import com.paypilot.merchant.repository.MerchantRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Merchant createMerchant(
            String merchantCode,
            String name,
            String email
    ) {
        if (merchantRepository.findByMerchantCode(merchantCode).isPresent()) {
            throw new IllegalArgumentException("Merchant code already exists");
        }

        if (merchantRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Merchant email already exists");
        }

        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        Merchant merchant = new Merchant(
                UUID.randomUUID(),
                merchantCode,
                name,
                email,
                "ACTIVE",
                now,
                now
        );

        return merchantRepository.save(merchant);
    }

    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }
}