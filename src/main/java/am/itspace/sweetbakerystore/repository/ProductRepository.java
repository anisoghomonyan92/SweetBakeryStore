package am.itspace.sweetbakerystore.repository;

import am.itspace.sweetbakerystore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    long count();
    @Query(value = " SELECT SUM(p.price * user_order.count) FROM user_order "
            + "JOIN product p ON user_order.product_id = p.id ", nativeQuery = true)
    double totalSale();

}
