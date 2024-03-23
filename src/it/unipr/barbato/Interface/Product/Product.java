package it.unipr.barbato.Interface.Product;

import java.io.Serializable;
/**
 * The {@code Product} interface represents a product.
 * It provides methods to get the price and serial number of the product.
 * 
 * @author Vincenzo Barbato 345728
 */
public interface Product extends Serializable {
	/**
	 * Returns the price of the product.
	 *
	 * @return The current price of the product.
	 */
	int getPrice();

	/**
	 * Returns the serial number of the product.
	 *
	 * @return The serial number of the product.
	 */
	int getSN();
}
