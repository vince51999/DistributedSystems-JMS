package it.unipr.barbato.Interface.Product;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * The {@code ProductsList} interface represents a list of products.
 * It provides methods to retrieve products by serial number, get a list of all possible serial numbers, and get the size of the list.
 * 
 * @author Vincenzo Barbato 345728
 */
public interface ProductsList extends Serializable {

	/**
	 * Returns the product with the specified serial number.
	 * 
	 * @param sn The serial number of the product
	 * @return The product with the specified serial number
	 */
	Product getProduct(int sn);

	/**
	 * Returns a list of all possible product serial numbers.
	 * 
	 * @return A list of all possible product serial numbers
	 */
	ArrayList<Integer> getSNs();
	
	/**
	 * Returns the size of the list.
	 * 
	 * @return The size of the list
	 */
	int getSize();
}