package br.com.codenation.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {

		Double orderValue = items.stream()
				.filter(i -> productRepository.findById(i.getProductId()).isPresent())
				.mapToDouble( i ->{
					Product product = productRepository.findById(i.getProductId()).get();
					double value = 0.0;
					if (product.getIsSale())
						value = (i.getQuantity() * product.getValue() * 0.8);
					else
						value = (i.getQuantity() * product.getValue());
					return value;
				}).sum();

		return orderValue;
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {

		return ids.stream().filter(id -> productRepository.findById(id).isPresent())
				.map(id-> productRepository.findById(id).get())
				.collect(Collectors.toSet());
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return orders.stream().mapToDouble(order -> calculateOrderValue(order)).sum();
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {

		return findProductsById(productIds).stream()
				.collect(Collectors.groupingBy(Product::getIsSale));
	}

}