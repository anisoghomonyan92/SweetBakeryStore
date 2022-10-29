package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.Category;

import am.itspace.sweetbakerystore.repository.CategoryRepository;
import am.itspace.sweetbakerystore.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Page<Category> findPaginated(Pageable pageable) {

        return categoryRepository.findAll(pageable);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);

    }

    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    public void save(Category category, CurrentUser currentUser) throws Exception {
        category.setUser(currentUser.getUser());
        categoryRepository.save(category);
    }

    public Optional<Category> findByIdForEdit(int id) {
        return categoryRepository.findById(id);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Long getCountOfCategories() {
        return categoryRepository.count();
    }
}
