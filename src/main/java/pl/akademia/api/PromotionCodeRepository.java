package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.akademia.api.model.PromotionCode;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface PromotionCodeRepository extends JpaRepository<PromotionCode, Long> {

@Query(value = "SELECT pc.orderId FROM PromotionCode pc WHERE pc.promotionCode =: promotionCode")
List<Long> getUsedOrders(UUID promotionCode);

@Query(value = "SELECT pc.usedDate FROM PromotionCode pc WHERE pc.promotionCode =: promotionCode ")
    List<LocalDate> getUsedDates(UUID promotionCode);

@Query(value = "SELECT pc.clientId FROM PromotionCode pc WHERE pc.promotionCode =: promotionCode ")
    List<Long> getUsedClients(UUID promotionCode);

    @Query(value = "SELECT pc.clientId, pc.orderId, pc.usedDate FROM PromotionCode pc WHERE pc.promotionCode =: promotionCode ")
    List<PromotionCode> getUsedPromotionCode(UUID promotionCode);

    @Query(value = "SELECT pc FROM PromotionCode pc WHERE pc.promotionCode = :promotionCode")
    PromotionCode getPromotionCodeByCode(UUID promotionCode);

    @Query(value = "SELECT pc FROM PromotionCode pc WHERE pc.usePromotionCodeCounter > 0")
    List<PromotionCode> getActivePromotionCodeByCode();

    @Query(value = "SELECT pc FROM PromotionCode pc WHERE pc.usePromotionCodeCounter = 0")
    List<PromotionCode> getInactivePromotionCodeByCode();



    @Modifying
    @Query(value = "DELETE FROM PromotionCode pc WHERE pc.promotionCodeId = :id")
    int deletePromotionCodeById(Long id);

}

