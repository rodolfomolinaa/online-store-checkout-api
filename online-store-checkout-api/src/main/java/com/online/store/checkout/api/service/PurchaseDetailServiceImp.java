package com.online.store.checkout.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.store.checkout.api.model.PurchaseItem;
import com.online.store.checkout.api.repository.IPurchaseDetailRepository;

@Service
public class PurchaseDetailServiceImp implements IPurchaseDetailService {

	@Autowired
	private IPurchaseDetailRepository repository;

	@Override
	public void save(PurchaseItem purchaseDetail) {
		this.repository.save(purchaseDetail);
	}

}
