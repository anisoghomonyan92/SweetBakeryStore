package am.itspace.sweetbakerystore.repository;

import am.itspace.sweetbakerystore.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
