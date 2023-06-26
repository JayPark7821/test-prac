package kr.jay.productorderservice.product;

import static org.assertj.core.api.Assertions.*;

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
	private StubProductPort productPort = new StubProductPort();

	@BeforeEach
	void setUp() {
		productService = new ProductService(productPort);
	}

	@Test
	void 상품수정() {
		final Long productId = 1L;
		final UpdateProductRequest request = new UpdateProductRequest("상품 수정", 20000, DiscountPolicy.NONE);
		final Product product = new Product("상품명", 10000, DiscountPolicy.NONE);
		productPort.getProduct_will_return = product;
		productService.updateProduct(productId, request);

		assertThat(product.getProductName()).isEqualTo("상품 수정");
		assertThat(product.getPrice()).isEqualTo(20000);
	}

	private static class StubProductPort implements ProductPort {

		public Product getProduct_will_return;

		@Override
		public void save(final Product product) {

		}

		@Override
		public Product getProduct(final Long productId) {
			return getProduct_will_return;
		}
	}
}
