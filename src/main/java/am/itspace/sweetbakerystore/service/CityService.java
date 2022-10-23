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

    public City findByID(int id) {
        Optional<City> byId = cityRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        return byId.get();
    }

    public Optional<City> findByName(String name) {
        return cityRepository.findByName(name);
    }

    public Optional<City> findById(int cityId) {
        return cityRepository.findById(cityId);
    }

    public void deleteById(int id) {
        cityRepository.deleteById(id);
    }

    public void save(City city) {
        cityRepository.save(city);
    }
}
