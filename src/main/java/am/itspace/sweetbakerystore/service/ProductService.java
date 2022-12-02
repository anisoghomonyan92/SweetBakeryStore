package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.dto.BasketDto;
import am.itspace.sweetbakerystore.dto.BasketRequest;
import am.itspace.sweetbakerystore.entity.Category;
import am.itspace.sweetbakerystore.entity.FavoriteProduct;
import am.itspace.sweetbakerystore.entity.Product;
import am.itspace.sweetbakerystore.repository.CategoryRepository;
import am.itspace.sweetbakerystore.repository.FavoriteProductRepository;
import am.itspace.sweetbakerystore.repository.ProductRepository;
import am.itspace.sweetbakerystore.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final FavoriteProductRepository favoriteProductRepository;
    @Value("${sweet.bakery.store.images.folder}")
    private String folderPath;

    @Resource(name = "basketDto")
    private BasketDto basketDto;


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
        return productRepository.totalSale();
    }


    public void addBasket(CurrentUser currentUser, int productId, BasketRequest basketRequest) {
        Optional<Product> byProductId = productRepository.findById(productId);
        byProductId.ifPresent(product -> product.setId(productId));
    }

    public void addFavoriteProduct(@AuthenticationPrincipal CurrentUser currentUser,
                                   @RequestParam("productId") Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        optionalProduct.ifPresent(product -> {
            Optional<FavoriteProduct> favoriteProduct = favoriteProductRepository
                    .findByUserAndProduct(currentUser.getUser(), product);
            if (favoriteProduct.isEmpty()) {
                FavoriteProduct favProduct = new FavoriteProduct();
                favProduct.setUser(currentUser.getUser());
                favProduct.setCreateAt(new Date());
                favProduct.setProduct(product);
                favoriteProductRepository.save(favProduct);
            }
        });

    }

    public void save(Product product, @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<Product> editedProduct = productRepository.findById(product.getId());
        if (editedProduct.isPresent()) {
            product.setUser(currentUser.getUser());
            productRepository.save(product);
        }
    }

    public Optional<Product> findByID(int id) {
        return productRepository.findById(id);

    }

    public List<Product> getAllProducts(String keyword) {
        if (keyword != null && !keyword.equals(" ")) {
            return  productRepository.search(keyword);
        }
        return productRepository.findAll();
    }
}

