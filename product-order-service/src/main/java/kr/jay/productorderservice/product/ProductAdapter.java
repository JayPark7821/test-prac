package kr.jay.productorderservice.product;

import org.springframework.stereotype.Component;

/**
 * ProductAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/06/25
 */
@Component
class ProductAdapter implements ProductPort {
	private final ProductRepository productRepository;

	ProductAdapter(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void save(final Product product) {
		productRepository.save(product);
	}

	@Override
	public Product getProduct(final Long productId) {
		return productRepository.findById(productId)
			.orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
	}
}
