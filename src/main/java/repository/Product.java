package repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Product extends JpaRepository<Product, Integer> {

}