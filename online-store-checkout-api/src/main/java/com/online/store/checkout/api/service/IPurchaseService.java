package com.online.store.checkout.api.service;

import java.util.Optional;

import com.online.store.checkout.api.model.Purchase;

public interface IPurchaseService {

	public Purchase save(Purchase purchase);

	public Optional<Purchase> getEmployee(Long id);
}
