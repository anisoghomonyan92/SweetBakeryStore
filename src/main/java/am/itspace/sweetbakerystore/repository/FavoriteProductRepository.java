package am.itspace.sweetbakerystore.repository;

import am.itspace.sweetbakerystore.entity.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Integer> {

}
