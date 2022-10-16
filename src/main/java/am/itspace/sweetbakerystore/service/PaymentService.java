package am.itspace.sweetbakerystore.service;

import am.itspace.sweetbakerystore.entity.Payment;
import am.itspace.sweetbakerystore.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    public Page<Payment> findPaginated(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }
}
