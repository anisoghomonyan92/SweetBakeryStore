package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.Category;
import am.itspace.sweetbakerystore.entity.Product;
import am.itspace.sweetbakerystore.repository.CategoryRepository;
import am.itspace.sweetbakerystore.repository.ProductRepository;
import am.itspace.sweetbakerystore.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Value("${sweet.bakery.store.images.folder}")
    private String folderPath;


    public Page<Product> findPaginated(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public byte[] getProductImage(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }


    public void save(Product product, MultipartFile file, CurrentUser currentUser) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + filename);
            file.transferTo(newFile);
            product.setProductPic(filename);
        }
        product.setUser(currentUser.getUser());
        Category category = categoryRepository.getReferenceById(product.getCategory().getId());
        product.setCategory(category);
        productRepository.save(product);
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Long getCountOfProducts() {
       return productRepository.count();
    }

    public Double getAmount() {
      return   productRepository.totalSale();
    }

    public Optional<Product> findByID(int id) {
                 return productRepository.findById(id);

    }

}
