package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.Review;
import am.itspace.sweetbakerystore.repository.ReviewRepository;
import am.itspace.sweetbakerystore.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Page<Review> findPaginated(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review save(Review review, CurrentUser currentUser) {
        review.setDate(new Date());
        review.setUser(currentUser.getUser());
        return  reviewRepository.save(review);
    }
}
