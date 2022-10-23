package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.Address;
import am.itspace.sweetbakerystore.entity.City;
import am.itspace.sweetbakerystore.repository.AddressRepository;
import am.itspace.sweetbakerystore.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private  final CityRepository cityRepository;

    public Page<Address> findPaginated(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    public void deleteById(int id) {
        addressRepository.deleteById(id);
    }


    public Address saveAddress(Address address) {
       return addressRepository.save(address);
    }

    public Optional<Address> findByName(String name,City city) {
        return addressRepository.findByNameAndCity(name, city);
    }

    public void saveAddressCity(Address address) {
        addressRepository.save(address);

    }

    public Optional<Address> findByIdForEdit(int id) {
        return addressRepository.findById(id);
    }

    public void save(Address address, City city) {
        cityRepository.save(city);
        addressRepository.save(address);
    }
}
