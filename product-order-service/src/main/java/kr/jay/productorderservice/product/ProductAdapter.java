package kr.jay.productorderservice.product;

/**
 * ProductAdapter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/06/25
 */
class ProductAdapter implements ProductPort {
	private final ProductRepository productRepository;

	ProductAdapter(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void save(final Product product) {
		productRepository.save(product);
	}
}
