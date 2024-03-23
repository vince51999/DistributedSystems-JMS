package it.unipr.barbato.Model.Product;

import it.unipr.barbato.Interface.Product.ProductOffer;

/**
 * The {@code ProductOfferImpl} represents an implementation of the {@link ProductOffer}
 * interface. This class provides methods to set and retrieve information about
 * a product offer.
 * 
 * @author Vincenzo Barbato 345728
 */
public class ProductOfferImpl implements ProductOffer {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Client ID
	 */
	private String id;
	/**
	 * Product serial number
	 */
	private int sn;

	/**
	 * Client offer for a product
	 */
	private int offer;

	/**
	 * Server response to client offer
	 */
	private Boolean confirmed;
	/**
	 * Client is subscribed or un-subscribed from queue
	 */
	private Boolean subscribe;

	/**
	 * Constructor of ProductOfferImpl object Initializes confirmed to false, the
	 * price and serial number to 0
	 *
	 */
	public ProductOfferImpl() {
		this.id = "";
		this.sn = 0;
		this.offer = 0;
		this.confirmed = null;
		this.subscribe = null;
	}

	/**
	 * Constructor of ProductOfferImpl object with ID and subscription status.
	 * Initializes confirmed to false, the price and serial number to 0.
	 *
	 * @param id        The client ID
	 * @param subscribe The subscription status
	 */
	public ProductOfferImpl(String id, Boolean subscribe) {
		this.id = id;
		this.sn = 0;
		this.offer = 0;
		this.confirmed = null;
		this.subscribe = subscribe;
	}

	/**
	 * Sets the offer made by the client.
	 * 
	 * @param offer The client's offer
	 */
	public void setOffer(int offer) {
		this.offer = offer;
	}

	/**
	 * Sets the serial number of the product that the client wants.
	 * 
	 * @param sn The serial number of the product
	 */
	public void setSN(int sn) {
		this.sn = sn;
	}

	/**
	 * Sets the subscription status of the client.
	 * 
	 * @param subscribe The subscription status
	 */
	public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * Sets the client ID.
	 * 
	 * @param id The client ID
	 */
	public void setID(String id) {
		this.id = id;
	}

	@Override
	public int getOffer() {
		return this.offer;
	}

	@Override
	public void setConfirm(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	@Override
	public int getSN() {
		return this.sn;
	}

	@Override
	public Boolean getSubscribe() {
		return this.subscribe;
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public Boolean getConfirm() {
		return this.confirmed;
	}

}