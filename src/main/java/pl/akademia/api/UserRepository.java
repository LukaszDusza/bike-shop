package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.akademia.api.model.UserApp;

public interface UserRepository extends JpaRepository<UserApp, Long> {
}
