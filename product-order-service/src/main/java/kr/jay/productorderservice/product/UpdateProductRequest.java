package kr.jay.productorderservice.product;

import org.springframework.util.Assert;

/**
 * UpdateProductRequest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/06/26
 */
record UpdateProductRequest(
	String productName,
	int price,
	DiscountPolicy discountPolicy
) {

	UpdateProductRequest {
		Assert.hasText(productName, "상품명은 필수입니다.");
		Assert.isTrue(price > 0, "상품 가격은 0원보다 커야 합니다.");
		Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
	}
}
