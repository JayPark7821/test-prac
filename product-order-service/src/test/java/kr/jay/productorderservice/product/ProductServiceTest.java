package kr.jay.productorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * ProductServiceTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/06/25
 */
class ProductServiceTest {

	private ProductService productService;

	@BeforeEach
	void setUp() {
		productService = new ProductService();
	}

	@Test
	void 상품등록() {
		final String name = "상품명";
		final int price = 1000;
		final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
		final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);
		productService.addProduct(request);
	}

	private class ProductService {
		public void addProduct(final AddProductRequest request) {
			throw new IllegalStateException("ProductService::addProduct not implemented yet");
		}
	}

	private record AddProductRequest(String productName, int price, DiscountPolicy discountPolicy) {
		private AddProductRequest {
			Assert.hasText(productName, "상품명은 필수입니다.");
			Assert.isTrue(price > 0, "상품 가격은 0원보다 커야 합니다.");
			Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
		}

	}

	private enum DiscountPolicy {
		NONE

	}
}
