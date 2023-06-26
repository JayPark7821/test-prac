package kr.jay.productorderservice.product;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ProductRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/06/25
 */
interface ProductRepository extends JpaRepository<Product, Long> {
}
