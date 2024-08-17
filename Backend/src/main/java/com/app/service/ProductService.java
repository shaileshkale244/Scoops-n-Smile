package com.app.service;

import java.util.List;

import com.app.entities.Product;

public interface ProductService {

	Product addNewProduct(Product product);
	List<Product> getAllProducts();
	Product updateProduct(Long id,Product product);
	String deleteProduct(Long pid);
	Product getById(Long id);
}
