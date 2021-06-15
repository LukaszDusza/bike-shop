package pl.akademia.api.promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface PromotionCodeRepository extends JpaRepository<PromotionCode, Long> {

    @Query(value = "SELECT pc.orderId FROM PromotionCode pc WHERE pc.promotionCode =: promotionCode")
    List<Long> getUsedOrders(String promotionCode);

    @Query(value = "SELECT pc.usedDate FROM PromotionCode pc WHERE pc.promotionCode =: promotionCode ")
    List<LocalDate> getUsedDates(String promotionCode);

    @Query(value = "SELECT pc.orderId, pc.usedDate FROM PromotionCode pc WHERE pc.promotionCode =: promotionCode ")
    List<PromotionCode> getUsedPromotionCode(String promotionCode);

    @Query(value = "SELECT pc FROM PromotionCode pc WHERE pc.promotionCode = :promotionCode")
    PromotionCode getPromotionCodeByCode(String promotionCode);

    @Query(value = "SELECT pc FROM PromotionCode pc WHERE pc.usePromotionCodeCounter > 0")
    List<PromotionCode> getActivePromotionCodeByCode();

    @Query(value = "SELECT pc FROM PromotionCode pc WHERE pc.usePromotionCodeCounter = 0")
    List<PromotionCode> getInactivePromotionCodeByCode();



    @Modifying
    @Query(value = "DELETE FROM PromotionCode pc WHERE pc.id = :id")
    int deletePromotionCodeById(Long id);

}

