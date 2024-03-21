package it.unipr.barbato.Controller;

import java.util.ArrayList;
import java.util.Random;

import it.unipr.barbato.Model.MessageHandler;
import it.unipr.barbato.Model.ServerOnMessage;

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
	
	private static final String BROKER_URL = "tcp://localhost:61616";
	
	/**
	 * Runnable method to run client
	 * @param args Arguments for main method
	 * @throws Exception If there is a problem
	 */
	public static void main(String[] args) throws Exception {

		//ProductOffer myOffer = new ProductOfferImpl();
		try {
			MessageHandler messageHandler = new MessageHandler(BROKER_URL);
			ServerOnMessage onMessage = new ServerOnMessage();
			messageHandler.setOnMessage(onMessage);
			messageHandler.subscribe("productslist");
		} catch (Exception e) {
			e.printStackTrace();
		}

//		ProductsOffersList offersList = (ProductsOffersList) registry.lookup("offersList");
//		offersList.subscribe(myOffer);
//
//		int productsCount = 0;
//		
//		// Client didn't end to purchases
//		while (productsCount < PURCHASES) {
//			ArrayList<Integer> sns = productsList.getSNs();
//			
//			// If list of SN is empty client waits
//			if (sns.size() > 0) {
//				int sn = getRandomSN(sns);
//				int offer = getRandomOffer();
//				int price = productsList.getProduct(sn).getPrice();
//				((ProductOfferImpl) myOffer).setSN(sn);
//				((ProductOfferImpl) myOffer).setOffer(offer);
//				System.out.println("-------------------------");
//				System.out.println("SN: " + sn + " Price: " + price + " Offer: " + offer);
//
//				Boolean confirm = null;
//				while (confirm == null) {
//					Thread.sleep(1000);
//					confirm = ((ProductOfferImpl) myOffer).getConfirm();
//				}
//				System.out.println("-------------------------");
//				if (confirm) {
//					productsCount++;
//					System.out.println("Server has accepted offer! Product with sn: " + sn
//							+ " has been bought at price: " + offer + "$");
//				} else {
//					System.out.println(
//							"Server has rejected the offered price: " + offer + "$ " + " for product with sn: " + sn);
//				}
//
//				myOffer.setConfirm(null);
//				((ProductOfferImpl) myOffer).setOffer(0);
//				((ProductOfferImpl) myOffer).setSN(0);
//			}
//			System.out.println(productsCount + "/" + PURCHASES);
//			Thread.sleep(2000);
//
//		}
//		System.out.println("Leave the shop.");
//		offersList.unsubcribe(myOffer);
//		UnicastRemoteObject.unexportObject(myOffer, false);
	}
	
	/**
	 * Return a random product SN
	 * @param array ArrayList of products SN
	 * @return Random product SN
	 */
	public static int getRandomSN(ArrayList<Integer> array) {
		int rnd = new Random().nextInt(array.size());
		return array.get(rnd);
	}

	/**
	 * Return a random offer for a product
	 * @return Random offer between MIN_PRICE and MAX_PRICE
	 */
	public static int getRandomOffer() {
		return new Random().nextInt(MAX_PRICE - MIN_PRICE) + MIN_PRICE;
	}
}
