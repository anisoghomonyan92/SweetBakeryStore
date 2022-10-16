package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.City;
import am.itspace.sweetbakerystore.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findByID(int id){
        Optional<City> byId = cityRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        return byId.get();
    }


}
