package am.itspace.sweetbakerystore.repository;

import am.itspace.sweetbakerystore.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
