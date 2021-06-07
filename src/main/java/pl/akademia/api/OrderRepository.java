package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademia.api.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
