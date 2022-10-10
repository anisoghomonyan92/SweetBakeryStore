package am.itspace.sweetbakerystore.repository;

import am.itspace.sweetbakerystore.entity.Address;
import am.itspace.sweetbakerystore.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findByCity(City city);
}
