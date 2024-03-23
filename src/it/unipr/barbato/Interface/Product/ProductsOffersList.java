package it.unipr.barbato.Interface.Product;

import java.io.Serializable;

/**
 * The {@code ProductsOffersList} interface represents a list of products buyers.
 * It provides methods to subscribe and unsubscribe from the list.
 * 
 * @author Vincenzo Barbato 345728
 */
public interface ProductsOffersList extends Serializable {
	/**
	 * Subscribe to the list of products buyers.
	 * 
	 * @param offer The remote object of the client.
	 */
	void subscribe(final ProductOffer offer);

	/**
	 * Unsubscribe from the list of products buyers.
	 * 
	 * @param offer The remote object of the client.
	 */
	void unsubscribe(final ProductOffer offer);
}
