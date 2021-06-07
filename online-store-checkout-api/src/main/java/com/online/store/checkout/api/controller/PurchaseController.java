package com.online.store.checkout.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.online.store.checkout.api.model.Product;
import com.online.store.checkout.api.model.ProductResponse;
import com.online.store.checkout.api.model.ProductWrapper;
import com.online.store.checkout.api.model.Purchase;
import com.online.store.checkout.api.model.PurchaseItem;
import com.online.store.checkout.api.service.PurchaseServiceImp;

@RestController
public class PurchaseController {
	@Autowired
	private PurchaseServiceImp purchaseService;

	@GetMapping("/purchase")
	public List<Purchase> getPurchases() {
		return this.purchaseService.getPurchases();
	}

	@GetMapping("/purchase/{id}")
	public Optional<Purchase> getPurchase(@PathVariable Long id) {
		return this.purchaseService.getPurchase(id);
	}

//	private final String PRODUCTS_API = "https://online-store-312700.wn.r.appspot.com/api/products/";
	private final String PRODUCTS_API = "http://localhost:5000/api/products/";

	@PostMapping("/purchase")
	public Purchase save(@RequestBody ProductWrapper products) {
		RestTemplate productRest = new RestTemplate();
		List<PurchaseItem> purchaseItems = new ArrayList<PurchaseItem>();

		Purchase purchase = new Purchase();
		float purchaseAmount = 0;
		int totalProducts = 0;

		try {
			for (Product product : products.getProducts()) {
				ProductResponse productResponse = productRest.getForObject(PRODUCTS_API + product.getId(),
						ProductResponse.class);
				System.out.println("productResponse with ID " + product.getId() + " " + productResponse);

				purchaseAmount += productResponse.getProduct().getPrice() * product.getQuantity();

				PurchaseItem item = new PurchaseItem();
				item.setProductId(productResponse.getProduct().getId());
				item.setPrice(productResponse.getProduct().getPrice());
				item.setQuantity(product.getQuantity());
				item.setTotal(productResponse.getProduct().getPrice() * product.getQuantity());
				item.setCreatedAt(new Date());
				purchaseItems.add(item);
			}
			totalProducts = products.getProducts().stream().mapToInt(product -> product.getQuantity()).sum();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		purchase.setAmount(purchaseAmount);
		purchase.setTotalProducts(totalProducts);
		purchase.setOrderNumber(UUID.randomUUID().toString());
		purchase.setCreatedAt(new Date());
		purchase.setPurchaseItems(purchaseItems);
		purchase = purchaseService.save(purchase);

		return purchase;
	}

}
