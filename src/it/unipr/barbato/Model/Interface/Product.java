package it.unipr.barbato.Model.Interface;
/**
 * The {@code Product} interface represents a product in a distributed system.
 * It provides methods to retrieve the price and serial number of the product.
 * 
 * @author Vincenzo Barbato 345728
 */
public interface Product extends java.io.Serializable {
	/**
	 * Returns the price of the product
	 * 
	 * @return The current price of the product
	 */
	int getPrice();

	/**
	 * Returns the serial number of the product
	 * 
	 * @return The serial number of the product
	 */
	int getSN();
}
