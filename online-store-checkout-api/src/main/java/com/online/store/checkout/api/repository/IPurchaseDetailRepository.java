package com.online.store.checkout.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.store.checkout.api.model.PurchaseItem;

public interface IPurchaseDetailRepository extends JpaRepository<PurchaseItem, Long> {

}
