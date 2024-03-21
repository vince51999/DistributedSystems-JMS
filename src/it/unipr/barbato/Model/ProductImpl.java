package it.unipr.barbato.Model;

import java.util.Random;

import it.unipr.barbato.Model.Interface.Product;

public class ProductImpl implements Product {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Product's price
	 */
	private int price;
	
	/**
	 * Product serial number
	 */
	private int sn;

	/**
	 * Constructor of ProductImpl object
	 * Initializes the price and serial number to 0
	 *
	 * @throws RemoteException If there is a problem with the remote object communication
	 */
	public ProductImpl() {
		this.price = 0;
		this.sn = 0;
	}
	/**
	 * Local method to set the serial number of the product
	 *
	 * @param sn The serial number of the product
	 */
	public void setSN(int sn) {
		this.sn = sn;
	}

	/**
	 * Local method to set the price of the product
	 *
	 * @param MAX_PRICE The maximum price for the product
	 * @param MIN_PRICE The minimum price for the product
	 */
	public void setPrice(int MAX_PRICE, int MIN_PRICE) {
		this.price = new Random().nextInt(MAX_PRICE - MIN_PRICE) + MIN_PRICE;
	}
	@Override
	public int getSN() {
		return this.sn;
	}
	@Override
	public int getPrice() {
		return this.price;
	}
}
