package kr.jay.productorderservice.product;

import static javax.persistence.GenerationType.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.Assert;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Product
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/06/25
 */

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
class Product {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	private String productName;
	private int price;
	private DiscountPolicy discountPolicy;

	public Product(final String productName, final int price, final DiscountPolicy discountPolicy) {
		Assert.hasText(productName, "상품명은 필수입니다.");
		Assert.isTrue(price > 0, "상품 가격은 0원보다 커야 합니다.");
		Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");

		this.productName = productName;
		this.price = price;
		this.discountPolicy = discountPolicy;
	}

}
