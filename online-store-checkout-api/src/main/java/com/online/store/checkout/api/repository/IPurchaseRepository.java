package com.online.store.checkout.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.store.checkout.api.model.Purchase;

public interface IPurchaseRepository extends JpaRepository<Purchase, Long> {

}
