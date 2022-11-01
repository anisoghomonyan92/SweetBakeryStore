package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.Order;
import am.itspace.sweetbakerystore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    @Value("${sweet.bakery.store.images.folder}")
    private String folderPath;

    public byte[] getProductImage(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }

    public Page<Order> findPaginated(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Long getCountOfOrders() {
        return orderRepository.count();
    }


    public Optional<Order> findById(int id) {
        return orderRepository.findById(id);
    }

}
