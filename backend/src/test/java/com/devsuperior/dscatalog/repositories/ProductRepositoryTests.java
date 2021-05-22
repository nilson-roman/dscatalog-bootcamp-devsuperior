package com.devsuperior.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {

	private long expectedId;
	private long notExistingId;
	private long countTotalProducts;
	
	@Autowired
	private ProductRepository repository;
	
	@BeforeEach
	void setUp() throws Exception{
		expectedId = 1L;
		notExistingId = 1000L;
		countTotalProducts = 25L;
	}
	
	@Test
	public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
		
		Product product = Factory.createProduct();
		product.setId(null);
		
		product = repository.save(product);
		
		Assertions.assertNotNull(product.getId());
		
		Assertions.assertEquals(countTotalProducts + 1, product.getId());
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(expectedId);
		
		Optional<Product> result = repository.findById(expectedId);
		
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {			
			repository.deleteById(notExistingId);
		});
	}
	
	@Test
	public void findByIdShouldReturnOptionalProductWhenIdExist() {
		Optional<Product> product = repository.findById(expectedId);
		
		Assertions.assertTrue(product.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnEmpityOptionalProductWhenIdNotExist() {

		Optional<Product> product = repository.findById(notExistingId);
		
		Assertions.assertTrue(product.isEmpty());

	}
}
