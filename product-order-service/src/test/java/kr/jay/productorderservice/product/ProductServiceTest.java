package kr.jay.productorderservice.product;

import java.util.HashMap;
import java.util.Map;

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
	private ProductPort productPort;
	private ProductRepository productRepository;

	@BeforeEach
	void setUp() {
		productRepository = new ProductRepository();
		productPort = new ProductAdapter(productRepository);
		productService = new ProductService(productPort);
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
		private final ProductPort productPort;

		private ProductService(final ProductPort productPort) {
			this.productPort = productPort;
		}

		public void addProduct(final AddProductRequest request) {
			final Product product = new Product(request.productName, request.price, request.discountPolicy);
			productPort.save(product);
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

	private class Product {
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

	private interface ProductPort {
		void save(final Product product);
	}

	private class ProductAdapter implements ProductPort {
		private final ProductRepository productRepository;

		private ProductAdapter(final ProductRepository productRepository) {
			this.productRepository = productRepository;
		}

		@Override
		public void save(final Product product) {
			productRepository.save(product);
		}
	}

	private class ProductRepository {
		private Map<Long, Product> persistence = new HashMap<>();
		private Long sequence = 0L;

		public void save(final Product product) {
			product.assignId(++sequence);
			persistence.put(product.getId(), product);
		}
	}
}
