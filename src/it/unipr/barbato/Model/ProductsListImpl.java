package it.unipr.barbato.Model;

import java.util.ArrayList;
import java.util.Set;

import it.unipr.barbato.Model.Interface.Product;
import it.unipr.barbato.Model.Interface.ProductsList;

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
}