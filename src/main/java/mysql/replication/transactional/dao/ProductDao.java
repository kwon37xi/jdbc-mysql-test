package mysql.replication.transactional.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mysql.replication.transactional.Product;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class ProductDao extends JdbcDaoSupport {
	public static class ProductRowMapper implements RowMapper<Product> {

		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			return null;
		}

	}

	public ProductDao() {
		System.out.println("## loading productDao...");
	}

	public List<Product> findAll() {
		return getJdbcTemplate().query("select /* findALl */ product_id, name, cnt, price from products",
			new BeanPropertyRowMapper<Product>(Product.class));
	}

	public Product findById(int id) {
		return getJdbcTemplate().queryForObject("select /* findById */ product_id, name, cnt, price from products where product_id=" + id,
			new BeanPropertyRowMapper<Product>(Product.class));
	}

	public void save(Product product) {
		getJdbcTemplate().update("insert into products (name, cnt, price) values (?,?,?)", product.getName(), product.getCnt(), product.getPrice());
	}
}