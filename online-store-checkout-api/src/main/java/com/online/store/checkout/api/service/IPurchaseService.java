package com.online.store.checkout.api.service;

import java.util.List;
import java.util.Optional;

import com.online.store.checkout.api.model.Purchase;

public interface IPurchaseService {

	public List<Purchase> getPurchases();

	public Optional<Purchase> getPurchase(Long id);

	public Purchase save(Purchase purchase);
}
