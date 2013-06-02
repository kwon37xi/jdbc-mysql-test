package mysql.replication.transactional.service;

import java.util.List;

import mysql.replication.transactional.Product;
import mysql.replication.transactional.dao.ProductDao;

import org.springframework.transaction.annotation.Transactional;

public class ProductService {
	private ProductDao productDao;

	public ProductService() {
		System.out.println("## loading productService...");
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Transactional(readOnly = false)
	public void save(Product product) {
		// Select도 함께 master로 날라가는지 테스트
		Product p = productDao.findById(1);
		System.out.println(p);

		productDao.save(product);
	}
}
