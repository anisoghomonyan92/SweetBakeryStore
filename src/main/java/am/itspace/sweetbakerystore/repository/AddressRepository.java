package am.itspace.sweetbakerystore.repository;

import am.itspace.sweetbakerystore.entity.Address;
import am.itspace.sweetbakerystore.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> getAllByCity(Address address);
    Collection<Object> findByCity(City city);

    Optional<Address> findByName(String name);

    Optional<Address> findByNameAndCity(String name,City city);
}
