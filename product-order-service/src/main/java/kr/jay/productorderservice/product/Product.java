package kr.jay.productorderservice.product;

import org.springframework.util.Assert;

/**
 * Product
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/06/25
 */
class Product {
	private Long id;
	private final String productName;
	private final int price;
	private final DiscountPolicy discountPolicy;

	public Product(final String productName, final int price, final DiscountPolicy discountPolicy) {
		Assert.hasText(productName, "상품명은 필수입니다.");
		Assert.isTrue(price > 0, "상품 가격은 0원보다 커야 합니다.");
		Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");

		this.productName = productName;
		this.price = price;
		this.discountPolicy = discountPolicy;
	}

	public void assignId(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
