package kr.jay.productorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * ProductServiceTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/06/26
 */
class ProductServiceTest {

	private ProductService productService;

	@BeforeEach
	void setUp() {
		final ProductPort productPort = new ProductPort() {
			@Override
			public void save(final Product product) {

			}

			@Override
			public Product getProduct(final Long productId) {
				return null;
			}
		};
		productService = new ProductService(productPort);
	}

	@Test
	void 상품수정() {
		final Long productId = 1L;
		final UpdateProductRequest request = new UpdateProductRequest("상품 수정", 20000, DiscountPolicy.NONE);

		productService.updateProduct(productId, request);
	}

}
