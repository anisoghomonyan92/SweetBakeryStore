package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.Address;
import am.itspace.sweetbakerystore.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Page<Address> findPaginated(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    public void deleteById(int id) {
        addressRepository.deleteById(id);
    }
}