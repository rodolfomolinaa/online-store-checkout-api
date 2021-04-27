package com.online.store.checkout.api.model;

import java.util.List;

public class ProductWrapper {
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Products [product=" + products + "]";
	}

}
