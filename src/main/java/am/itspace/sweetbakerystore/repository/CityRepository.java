package am.itspace.sweetbakerystore.repository;

import am.itspace.sweetbakerystore.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByName(String name);

}
