package am.itspace.sweetbakerystore.repository;

import am.itspace.sweetbakerystore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
