package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.repository.FavoriteProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteProductService {
    private final FavoriteProductRepository favoriteProductRepository;

    public Long getCountOfFavoriteProducts() {
        return favoriteProductRepository.count();
    }
}
