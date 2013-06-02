package mysql.replication.transactional;

import mysql.replication.transactional.service.ProductService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class ServiceCaller {

	public static final String CONTEXT_XML_PATH = "classpath:applicationContext.xml";

	private ApplicationContext applicationContext;

	private ProductService productService;

	public ServiceCaller() {
		applicationContext = new ClassPathXmlApplicationContext(CONTEXT_XML_PATH);
		productService = applicationContext.getBean(ProductService.class);
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public ProductService getProductService() {
		return productService;
	}
}
