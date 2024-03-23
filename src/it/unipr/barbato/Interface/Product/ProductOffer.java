package it.unipr.barbato.Interface.Product;

import java.io.Serializable;
/**
 * The {@code ProductOffer} interface represents an offer for a product in a distributed system.
 * It provides methods to retrieve the offer, the serial number of the product, and to confirm the client's offer.
 * 
 * @author Vincenzo Barbato 345728
 */
public interface ProductOffer extends Serializable {

	/**
	 * Returns the offer for the product.
	 *
	 * @return The client's offer.
	 */
	int getOffer();

	/**
	 * Returns the serial number of the product.
	 *
	 * @return The serial number of the product.
	 */
	int getSN();

	/**
	 * Returns the ID of the product.
	 *
	 * @return The ID of the product.
	 */
	String getID();

	/**
	 * Returns if the client is subscribed or not.
	 *
	 * @return If the client is subscribed or not.
	 */
	Boolean getSubscribe();

	/**
	 * Local method to get the confirmation status of the offer.
	 *
	 * @return True if the offer is confirmed, false otherwise.
	 */
	Boolean getConfirm();

	/**
	 * Confirms the client's offer.
	 *
	 * @param confirmed The server's offer response.
	 */
	void setConfirm(Boolean confirmed);
}
