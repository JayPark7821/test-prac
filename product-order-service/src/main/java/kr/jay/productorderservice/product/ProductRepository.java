package kr.jay.productorderservice.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * ProductRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/06/25
 */
@Repository
class ProductRepository {
	private Map<Long, Product> persistence = new HashMap<>();
	private Long sequence = 0L;

	public void save(final Product product) {
		product.assignId(++sequence);
		persistence.put(product.getId(), product);
	}
}
