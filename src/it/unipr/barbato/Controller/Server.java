package it.unipr.barbato.Controller;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import it.unipr.barbato.Interface.Product.Product;
import it.unipr.barbato.Interface.Product.ProductsList;
import it.unipr.barbato.Model.Message.MessageHandlerImpl;
import it.unipr.barbato.Model.Message.ServerOffersHandler;
import it.unipr.barbato.Model.Product.ProductImpl;
import it.unipr.barbato.Model.Product.ProductsListImpl;

/**
 * The {@code Server} class represents the server component of the application.
 * It manages the clients and offers, as well as the list of products.
 * 
 * @author Vincenzo Barbato 345728
 */
public class Server {
	/**
	 * Min number of clients to open the shop
	 */
	private static final int MIN_CLIENTS = 3;
	/**
	 * Min price for product
	 */
	private static final int MIN_PRICE = 10;
	/**
	 * Max price for product
	 */
	private static final int MAX_PRICE = 200;

	/**
	 * The list of products.
	 */
	private static ProductsList productsList = null;

	/**
	 * Runnable method to run the server.
	 *
	 * @param args Arguments for the main method
	 * @throws Exception If there is a problem running the server
	 */
	public static void main(String[] args) throws Exception {
		MessageHandlerImpl messageHandler = new MessageHandlerImpl();
		messageHandler.start();

		// Create list of clients offers, manage clients and offers
		ServerOffersHandler offersList = new ServerOffersHandler(messageHandler);
		offersList.setProductList(productsList);
		offersList.start();

		// Create list of products
		Set<Product> products = productsList(3 * 10);
		productsList = new ProductsListImpl(products);
		messageHandler.publish("productsList", productsList);

		// Wait for 3 clients
		while (offersList.getSize() < MIN_CLIENTS) {
			System.out.println("Wait clients: " + offersList.getSize() + "/" + MIN_CLIENTS);
			Thread.sleep(2000);
		}

		// There are clients that want to buy
		while (offersList.getSize() > 0) {
			updateProductList();
			offersList.setProductList(productsList);
			messageHandler.publish("productsList", productsList);
			Thread.sleep(2000);
		}
		messageHandler.close();
		offersList.close();
	}

	/**
	 * Create a list of products with prices between (MAX_PRICE, MIN_PRICE) and SN
	 * between (1, num_product).
	 *
	 * @param num_products Number of products
	 * @return List of products
	 */
	private static Set<Product> productsList(int num_products) {
		Set<Product> products = new CopyOnWriteArraySet<>();
		for (int i = 0; i < num_products; i++) {
			ProductImpl product = new ProductImpl();
			product.setSN(i + 1);
			product.setPrice(MAX_PRICE, MIN_PRICE);
			products.add(product);
		}
		return products;
	}

	/**
	 * Update the product list with new prices.
	 */
	public static void updateProductList() {
		Set<Product> products;
		products = productsList(3 * 10);
		((ProductsListImpl) productsList).setProducts(products);
		System.out.println("Updated prices");
	}
}
