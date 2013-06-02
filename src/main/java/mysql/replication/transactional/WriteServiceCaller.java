package mysql.replication.transactional;

import java.io.IOException;

public class WriteServiceCaller extends ServiceCaller {

	public void doJob(String[] args) throws IOException {
		Product product = new Product();

		product.setName(args[0]);
		product.setCnt(Integer.parseInt(args[1]));
		product.setPrice(Integer.parseInt(args[2]));

		getProductService().save(product);
	}

	public static void main(String[] args) throws IOException {
		WriteServiceCaller writeServiceCaller = new WriteServiceCaller();

		writeServiceCaller.doJob(args);
	}
}