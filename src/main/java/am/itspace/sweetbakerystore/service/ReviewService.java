package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.Review;
import am.itspace.sweetbakerystore.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;


    public Page<Review> findPaginated(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }
}
