package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.akademia.api.model.PromoCode;

import java.util.List;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {

@Query(value = "SELECT pc.orderId FROM PromoCode pc WHERE pc.promoCodeId =: promoCodeId")
List<Long> getOrderUsed(Long promoCodeId);
}
