package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.Address;
import am.itspace.sweetbakerystore.entity.Category;
import am.itspace.sweetbakerystore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Page<Category> findPaginated(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public List<Category> findByName(String name) {
        if (name ==null || name.equals(" ")) {
            return categoryRepository.findAll();
        }else{
            return categoryRepository.findByName(name);
        }

    }

    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
}
