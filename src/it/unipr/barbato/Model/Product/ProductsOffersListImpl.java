package it.unipr.barbato.Model.Product;

import java.util.Set;

import it.unipr.barbato.Interface.Product.ProductOffer;
import it.unipr.barbato.Interface.Product.ProductsOffersList;

/**
 * The {@code ProductsOffersListImpl} is an implementation of the {@link ProductsOffersList} interface.
 * Represents a list of client's offers.
 * 
 * @author Vincenzo Barbato 345728
 */
public class ProductsOffersListImpl implements ProductsOffersList {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * List of client's offers
	 */
	private Set<ProductOffer> offers;
	
	/**
	 * Constructor of ProductsOffersListImpl object
	 * 
	 * @param offers List of client offers
	 */
	public ProductsOffersListImpl(Set<ProductOffer> offers) {
		this.offers = offers;
	}

	@Override
	public void subscribe(ProductOffer o) {
		this.offers.add(o);
	}

	@Override
	public void unsubscribe(ProductOffer o) {
		this.offers.remove(o);
	}

}
