package am.itspace.sweetbakerystore.repository;


import am.itspace.sweetbakerystore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
