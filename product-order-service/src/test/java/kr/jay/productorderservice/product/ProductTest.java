package kr.jay.productorderservice.product;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * ProductTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/06/26
 */
class ProductTest {

	@Test
	void update() {
		final Product product = new Product("상품명", 10000, DiscountPolicy.NONE);
		product.update("상품 수정", 20000, DiscountPolicy.NONE);

		assertThat(product.getProductName()).isEqualTo("상품 수정");
		assertThat(product.getPrice()).isEqualTo(20000);
	}
}