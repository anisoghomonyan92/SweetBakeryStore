package am.itspace.sweetbakerystore.repository;

import am.itspace.sweetbakerystore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);

    long count();

}
