package it.unipr.barbato.Controller;

import java.util.ArrayList;
import java.util.Random;

import it.unipr.barbato.Interface.Product.ProductsList;
import it.unipr.barbato.Model.Message.ClientOfferHandler;
import it.unipr.barbato.Model.Message.ClientProductsHandler;
import it.unipr.barbato.Model.Message.MessageHandlerImpl;
import it.unipr.barbato.Model.Message.RequestType;

/**
 * The {@code Client} class represents a client that interacts with the shop system.
 * It subscribes to the products list, creates client offers, and sends them to the server.
 * The client continues to make purchases until a specified number of purchases is reached.
 * 
 * @author Vincenzo Barbato 345728
 */
public class Client {
	/**
	 * Min price for product
	 */
	private static final int MIN_PRICE = 10;
	/**
	 * Max price for product
	 */
	private static final int MAX_PRICE = 200;
	/**
	 * Number of purchases that client do before logout from the shop
	 */
	private static final int PURCHASES = 10;

	/**
	 * Runnable method to run client
	 * 
	 * @param args Arguments for main method
	 * @throws Exception If there is a problem
	 */
	public static void main(String[] args) throws Exception {

		MessageHandlerImpl messageHandler = new MessageHandlerImpl();
		messageHandler.start();

		// Create client offer
		ClientOfferHandler myOffer = new ClientOfferHandler(messageHandler);
		myOffer.start();

		// Client subscribe to products list
		ClientProductsHandler productsList = new ClientProductsHandler(messageHandler);
		productsList.start();

		// Client don't end to purchases
		while (myOffer.getProductsCount() < PURCHASES) {
			ProductsList list = productsList.getProductsList();
			if (list != null) {
				ArrayList<Integer> sns = list.getSNs();
				if (sns.size() > 0) {
					int sn = getRandomSN(sns);
					int offer = getRandomOffer();
					int price = productsList.getProductsList().getProduct(sn).getPrice();
					myOffer.setClientOffer(sn, offer, null);

					System.out.println("-------------------------");
					System.out.println("SN: " + sn + " Price: " + price + " Offer: " + offer);

					messageHandler.send("offersList", myOffer.getClientOffer(), RequestType.offer);

				}
				System.out.println(myOffer.getProductsCount() + "/" + PURCHASES);
				Thread.sleep(2000);
			}
			Thread.sleep(2000);
		}
		myOffer.close();
		productsList.close();
		messageHandler.close();
	}

	/**
	 * Return a random product SN
	 * 
	 * @param sns ArrayList of products SN
	 * @return Random product SN
	 */
	private static int getRandomSN(ArrayList<Integer> sns) {
		int rnd = new Random().nextInt(sns.size());
		return sns.get(rnd);
	}

	/**
	 * Return a random offer for a product
	 * 
	 * @return Random offer between MIN_PRICE and MAX_PRICE
	 */
	private static int getRandomOffer() {
		return new Random().nextInt(MAX_PRICE - MIN_PRICE) + MIN_PRICE;
	}
}
