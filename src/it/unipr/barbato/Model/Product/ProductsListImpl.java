package it.unipr.barbato.Model.Product;

import java.util.ArrayList;
import java.util.Set;

import it.unipr.barbato.Interface.Product.Product;
import it.unipr.barbato.Interface.Product.ProductsList;

/**
 * The {@code ProductsListImpl} represents an implementation of the {@link ProductsList} interface.
 * It provides a list of products and methods to access and manipulate the list.
 * 
 * @author Vincenzo Barbato 345728
 */
public class ProductsListImpl implements ProductsList {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * List of products available
	 */
	private Set<Product> products;

	/**
	 * Constructor of ProductsListImpl object
	 * Initializes the products with the specified set of products
	 * 
	 * @param p The set of products
	 */
	public ProductsListImpl(final Set<Product> p) {
		this.products = p;
	}
	
	/**
	 * Sets the products in the list to the specified set of products.
	 * 
	 * @param p The set of products
	 */
	public void setProducts(final Set<Product> p) {
		this.products = p;
	}

	@Override
	public Product getProduct(int sn) {
		for (Product p : this.products) {
			if (p.getSN() == sn) {
				return p;
			}
		}
		return new ProductImpl();
	}

	@Override
	public ArrayList<Integer> getSNs() {
		ArrayList<Integer> sns = new ArrayList<>();

		for (Product p : this.products) {
			sns.add(p.getSN());
		}
		return sns;
	}
	
	@Override
	public int getSize() {
		return this.products.size();
	}
}