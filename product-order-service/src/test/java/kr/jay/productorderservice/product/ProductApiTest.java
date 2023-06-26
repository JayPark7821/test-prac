package kr.jay.productorderservice.product;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import kr.jay.productorderservice.ApiTest;

/**
 * ProductServiceTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/06/25
 */

class ProductApiTest extends ApiTest {

	@Autowired
	private ProductService productService;

	@Test
	void 상품등록() {
		final var request = ProductSteps.상품등록요청_생성();

		final var response = ProductSteps.상품등록요청(request);

		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

	}

	@Test
	void 상품조회() {
		ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
		final Long productId = 1L;
		
		final var response = ProductSteps.상품조회요청(productId);

		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.jsonPath().getString("name")).isEqualTo("상품명");
	}

}
