package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.akademia.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
