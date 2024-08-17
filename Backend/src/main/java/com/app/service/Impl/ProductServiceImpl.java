package com.app.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.CustomException;
import com.app.entities.Product;
import com.app.repository.ProductRepository;
import com.app.service.ProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addNewProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product updateProduct(Long id,Product product) {
		if(productRepository.existsById(id)) {
		return productRepository.save(product);
		}
		throw new CustomException("invalid product");
	}

	@Override
	public String deleteProduct(Long pid) {
		if(productRepository.existsById(pid)) {
			productRepository.deleteById(pid);
			return "Product Deleted";
		}
		throw new CustomException("Delete operation failed");
	}
	
	@Override
	public Product getById(Long id) {
		Optional<Product> optional = productRepository.findById(id);
		return optional.orElseThrow();
	}

}
