package pl.akademia.api.promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {

@Query(value = "SELECT pc.orderId FROM PromoCode pc WHERE pc.promoCode =: promoCode")
List<Long> getUsedOrders(UUID promoCode);

@Query(value = "SELECT pc.usedDate FROM PromoCode pc WHERE pc.promoCode =: promoCode ")
    List<LocalDate> getUsedDates(UUID promoCode);

@Query(value = "SELECT pc.clientId FROM PromoCode pc WHERE pc.promoCode =: promoCode ")
    List<Long> getUsedClients(UUID promoCode);

    @Query(value = "SELECT pc.clientId, pc.orderId, pc.usedDate FROM PromoCode pc WHERE pc.promoCode =: promoCode ")
    List<PromoCode> getUsedPromoCode(UUID promoCode);

}

