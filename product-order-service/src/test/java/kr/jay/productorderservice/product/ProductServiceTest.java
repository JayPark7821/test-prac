package kr.jay.productorderservice.product;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ProductServiceTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/06/26
 */

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Test
	void 상품조회() {
		// 상품 등록
		productService.addProduct(ProductSteps.상품등록요청_생성());
		final Long productId = 1L;

		// 상품 조회
		final GetProductResponse response = productService.getProduct(productId);
		// 상품 응답 검증
		assertThat(response).isNotNull();
	}

}
