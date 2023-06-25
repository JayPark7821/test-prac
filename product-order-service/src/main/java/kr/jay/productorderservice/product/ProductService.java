package kr.jay.productorderservice.product;

/**
 * ProductService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/06/25
 */
class ProductService {
	private final ProductPort productPort;

	ProductService(final ProductPort productPort) {
		this.productPort = productPort;
	}

	public void addProduct(final AddProductRequest request) {
		final Product product = new Product(request.productName(), request.price(), request.discountPolicy());
		productPort.save(product);
	}
}
