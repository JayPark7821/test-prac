package kr.jay.productorderservice.product;

import org.springframework.util.Assert;

/**
 * GetProductResponse
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/06/26
 */
record GetProductResponse(
	Long id,
	String name,
	int price,
	DiscountPolicy discountPolicy
) {
	GetProductResponse {
		Assert.notNull(id, "id must not be null");
		Assert.notNull(name, "name must not be null");
		Assert.notNull(discountPolicy, "discountPolicy must not be null");

	}
}
