package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.Order;
import am.itspace.sweetbakerystore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    @Value("${sweet.bakery.store.images.folder}")
    private String folderPath;

    public List<Order>  findAllOrders() {
        return  orderRepository.findAll();
    }

    public byte[] getProductImage(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }

    public Page<Order> findPaginated(Pageable  pageable) {
        return orderRepository.findAll(pageable);
    }
}
