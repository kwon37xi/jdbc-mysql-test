package mysql.replication.transactional;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Product implements Serializable {

	private int productId;
	private String name;
	private int cnt;
	private int price;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", cnt=" + cnt + ", price=" + price + "]";
	}

}
