package am.itspace.sweetbakerystore.repository;


import am.itspace.sweetbakerystore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);

}
