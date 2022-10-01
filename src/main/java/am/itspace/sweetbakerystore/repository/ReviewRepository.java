package am.itspace.sweetbakerystore.repository;

import am.itspace.sweetbakerystore.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
