package com.online.store.checkout.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.store.checkout.api.model.Purchase;
import com.online.store.checkout.api.repository.IPurchaseRepository;

@Service
public class PurchaseServiceImp implements IPurchaseService {

	@Autowired
	private IPurchaseRepository repository;

	@Override
	public Purchase save(Purchase purchase) {
		return this.repository.save(purchase);
	}

	@Override
	public Optional<Purchase> getEmployee(Long id) {
		Optional<Purchase> purchase = this.repository.findById(id);
		return purchase;
	}

}
