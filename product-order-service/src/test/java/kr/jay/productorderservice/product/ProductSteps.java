package kr.jay.productorderservice.product;

import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class ProductSteps {
	public static ExtractableResponse<Response> 상품등록요청(final AddProductRequest request) {
		return RestAssured.given()
			.log()
			.all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(request)
			.when()
			.post("/products")
			.then()
			.log()
			.all()
			.extract();
	}

	public static AddProductRequest 상품등록요청_생성() {
		return new AddProductRequest("상품명", 1000, DiscountPolicy.NONE);
	}
}