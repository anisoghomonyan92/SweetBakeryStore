package am.itspace.sweetbakerystore.repository;

import am.itspace.sweetbakerystore.entity.FavoriteProduct;
import am.itspace.sweetbakerystore.entity.Product;
import am.itspace.sweetbakerystore.entity.User;
import am.itspace.sweetbakerystore.security.CurrentUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Integer> {
    long count();

    Optional<FavoriteProduct> findByUserAndProduct(User user, Product product);

    List<FavoriteProduct> getByUser(User user);
}
