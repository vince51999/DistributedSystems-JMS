package it.unipr.barbato.Model.Interface;

import java.util.ArrayList;

/**
 * The {@code ProductsList} interface represents a list of products in a distributed system.
 * It provides methods to retrieve product information.
 * 
 * @author Vincenzo Barbato 345728
 */
public interface ProductsList extends java.io.Serializable {

	/**
	 * Returns the product with the specified serial number
	 * 
	 * @param sn The serial number of the product
	 * @return The product with the specified serial number
	 */
	Product getProduct(int sn);

	/**
	 * Returns a list of all possible product serial numbers
	 * 
	 * @return A list of all possible product serial numbers
	 */
	ArrayList<Integer> getSNs();
}