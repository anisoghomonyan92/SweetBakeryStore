package am.itspace.sweetbakerystore.repository;

import am.itspace.sweetbakerystore.entity.Address;
import am.itspace.sweetbakerystore.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface AddressRepository extends JpaRepository<Address, Integer> {


    Collection<Object> findByCity(City city);
}
