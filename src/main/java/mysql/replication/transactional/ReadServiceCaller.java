package mysql.replication.transactional;

import java.util.List;

public class ReadServiceCaller extends ServiceCaller {

	public void doJob() {
		List<Product> products = getProductService().findAll();

		for (Product product : products) {
			System.out.println(product);
		}
	}

	public static void main(String[] args) {
		ReadServiceCaller readServiceCaller = new ReadServiceCaller();

		readServiceCaller.doJob();
	}
}
