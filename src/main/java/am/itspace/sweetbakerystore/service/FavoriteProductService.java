package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.FavoriteProduct;
import am.itspace.sweetbakerystore.entity.Product;
import am.itspace.sweetbakerystore.repository.FavoriteProductRepository;
import am.itspace.sweetbakerystore.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteProductService {
    private final FavoriteProductRepository favoriteProductRepository;

    public Long getCountOfFavoriteProducts() {
        return favoriteProductRepository.count();
    }

    public Page<FavoriteProduct> findPaginated(Pageable pageable) {
        return favoriteProductRepository.findAll(pageable);
    }

    public List<FavoriteProduct> getByUser(CurrentUser currentUser) {
        return favoriteProductRepository.getByUser(currentUser.getUser());
    }
}
