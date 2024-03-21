package it.unipr.barbato.Controller;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.apache.activemq.ActiveMQConnection;

import jakarta.jms.JMSException;
import it.unipr.barbato.Model.MessageHandler;
import it.unipr.barbato.Model.ProductImpl;
import it.unipr.barbato.Model.ProductsListImpl;
import it.unipr.barbato.Model.Interface.Product;
import it.unipr.barbato.Model.Interface.ProductsList;

public class Server {
	/**
	 * Min price for product
	 */
	private static final int MIN_PRICE = 10;
	/**
	 * Max price for product
	 */
	private static final int MAX_PRICE = 200;

	/**
	 * Min number of clients to open the shop
	 */
	private static final int MIN_CLIENTS = 3;
	private static final String BROKER_URL = "tcp://localhost:61616";
	static ActiveMQConnection connection = null;

	/**
	 * Runnable method to run server
	 * 
	 * @param args Arguments for main method
	 * @throws Exception If there is a problem
	 */
	public static void main(String[] args) throws JMSException {

		try {
			// Create list of products
			Set<Product> products = productsList(3 * 10);
			ProductsList productsList = new ProductsListImpl(products);
			MessageHandler messageHandler = new MessageHandler(BROKER_URL);
			messageHandler.publish("productslist", productsList);

//			// Create list of clients offers
//			Set<ProductOffer> offers = new CopyOnWriteArraySet<>();
//			ProductsOffersList offersList = new ProductsOffersListImpl(offers);
//			registry.rebind("offersList", offersList);
//
//			// Wait clients
//			while (offers.size() < MIN_CLIENTS) {
//				System.out.println("Wait clients: " + offers.size() + "/" + MIN_CLIENTS);
//				Thread.sleep(2000);
//			}
//
//			// There are clients that want buy
//			while (offers.size() > 0) {
//				for (Product product : products) {
//					((ProductImpl) product).setPrice(MAX_PRICE, MIN_PRICE);
//					System.out.println("--------------------");
//					System.out.println("SN: " + product.getSN() + " Price: " + product.getPrice() + "$");
//				}
//				
//				try {
//					Thread.sleep(2000);
//					// For each client check offer
//					for (ProductOffer offer : offers) {
//						int sn = offer.getSN();
//						int o = offer.getOffer();
//						if (o > 0 && sn > 0) {
//							Product p = productsList.getProduct(sn);
//
//							System.out.println("--------------------");
//							System.out.println("SN: " + sn + " Price: " + p.getPrice() + "$" + " Offer: " + o + "$");
//							if (p.getPrice() <= o) {
//								offer.setConfirm(true);
//								System.out.println("Offer accepted");
//							} else {
//								offer.setConfirm(false);
//								System.out.println("Offer rejected");
//							}
//						}
//						Thread.sleep(300);
//					}
//				} catch (Exception e) {
//					continue;
//				}
//			}
			System.out.println("All clients are disconnected. Shop close.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create a list of products with prices between (MAX_PRICE, MIN_PRICE) and SN
	 * between (1, num_product)
	 * 
	 * @param num_products Number of products
	 * @return List of products
	 * @throws Exception If there is a problem
	 */
	private static Set<Product> productsList(int num_products) throws Exception {
		Set<Product> products = new CopyOnWriteArraySet<>();
		for (int i = 0; i < num_products; i++) {
			ProductImpl product = new ProductImpl();
			product.setSN(i + 1);
			product.setPrice(MAX_PRICE, MIN_PRICE);
			products.add(product);
		}
		return products;
	}

}
