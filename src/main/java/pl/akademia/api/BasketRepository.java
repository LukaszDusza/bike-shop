package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademia.api.model.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
}
