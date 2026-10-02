package com.paypilot.merchant.repository;

import com.paypilot.merchant.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {

    Optional<Merchant> findByMerchantCode(String merchantCode);

    Optional<Merchant> findByEmail(String email);
}