package it.unipr.barbato.Model.Product;

import java.util.Random;

import it.unipr.barbato.Interface.Product.Product;

/**
 * The {@code ProductImpl} is implementation of the {@link Product} interface.
 * Represents a product with a price and serial number.
 * 
 * @author Vincenzo Barbato 345728
 */
public class ProductImpl implements Product {
	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Product's price.
	 */
	private int price;

	/**
	 * Product serial number.
	 */
	private int sn;

	/**
	 * Default constructor for ProductImpl.
	 * Initializes the price and serial number to 0.
	 */
	public ProductImpl() {
		this.price = 0;
		this.sn = 0;
	}

	/**
	 * Constructor for ProductImpl.
	 * Initializes the price and serial number with the given values.
	 *
	 * @param sn    The serial number of the product.
	 * @param price The price of the product.
	 */
	public ProductImpl(int sn, int price) {
		this.price = sn;
		this.sn = price;
	}

	/**
	 * Sets the serial number of the product.
	 *
	 * @param sn The serial number of the product.
	 */
	public void setSN(int sn) {
		this.sn = sn;
	}

	/**
	 * Sets the price of the product.
	 *
	 * @param MAX_PRICE The maximum price for the product.
	 * @param MIN_PRICE The minimum price for the product.
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
