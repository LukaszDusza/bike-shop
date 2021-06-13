package pl.akademia.api.promotion;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class PromotionCodeService {
/*   private final int maxMonthsExp = 6;
    private final int minMonthsExp = 1;
    private final BigDecimal maxDiscount = new BigDecimal(500);
    private final BigDecimal minDiscount = new BigDecimal(50);*/

    private final int maxMultipleUse = 20;


    private final PromotionCodeRepository promotionCodeRepository;

    public PromotionCodeService(PromotionCodeRepository promotionCodeRepository) {
        this.promotionCodeRepository = promotionCodeRepository;
    }

    public PromotionCode createPromotionCode(int activeDays, BigDecimal discount) {
        PromotionCode promotionCode = new PromotionCode();
        promotionCode.setPromotionCode(UUID.randomUUID());
        promotionCode.setGenerateDate(Date.valueOf(LocalDate.now()));
        promotionCode.setExpDate(Date.valueOf(promotionCode.getGenerateDate().toLocalDate().plusDays(activeDays)));
        promotionCode.setDiscount(discount);
        promotionCode.setMultipleUse(ThreadLocalRandom.current().nextBoolean());
        if (promotionCode.isMultipleUse()) {
            promotionCode.setUsePromotionCodeCounter(ThreadLocalRandom.current().nextInt(2, maxMultipleUse));
        } else
            promotionCode.setUsePromotionCodeCounter(1);
        return promotionCodeRepository.save(promotionCode);
    }

/*    public PromotionCode exampleCreatePromoCode() {
        PromotionCode promotionCode = new PromotionCode();
        promotionCode.setPromotionCode(UUID.randomUUID());
        promotionCode.setGenerateDate(Date.valueOf(LocalDate.now()));
        promotionCode.setExpDate(Date.valueOf(promotionCode.getGenerateDate().toLocalDate().plusMonths(ThreadLocalRandom.current().nextInt(minMonthsExp, maxMonthsExp + 1))));
        promotionCode.setDiscount(new BigDecimal(ThreadLocalRandom.current().nextInt(minDiscount.intValue(), maxDiscount.intValue())));
        promotionCode.setMultipleUse(ThreadLocalRandom.current().nextBoolean());
        if (promotionCode.isMultipleUse()) {
            promotionCode.setUsePromotionCodeCounter(ThreadLocalRandom.current().nextInt(2, maxMultipleUse));
        } else
            promotionCode.setUsePromotionCodeCounter(1);
        return promotionCodeRepository.save(promotionCode);
    }*/

    public List<Long> getUsedOrders(UUID promotionCode) {
        return promotionCodeRepository.getUsedOrders(promotionCode);
    }

    public List<LocalDate> getUsedDates(UUID promotionCode) {
        return promotionCodeRepository.getUsedDates(promotionCode);
    }

    public List<PromotionCode> getUsedPromotionCode(UUID promotionCode) {
        return promotionCodeRepository.getUsedPromotionCode(promotionCode);
    }

    public PromotionCode getPromotionCodeByCode(UUID promotionCode) {
        return promotionCodeRepository.getPromotionCodeByCode(promotionCode);
    }

    public List<PromotionCode> getActivePromotionCodeByCode(){
        return promotionCodeRepository.getActivePromotionCodeByCode();
    }

    public List<PromotionCode> getInactivePromotionCodeByCode(){
        return promotionCodeRepository.getInactivePromotionCodeByCode();
    }

    public PromotionCode usePromotionCode(UUID promotionCode) throws WrongPromotionCodeException {
        PromotionCode pC = getPromotionCodeByCode(promotionCode);
        if (pC == null) throw new WrongPromotionCodeException("Wrong or Used Promo Code");
        pC.setUsePromotionCodeCounter(pC.getUsePromotionCodeCounter() - 1);
        if (pC.getUsePromotionCodeCounter() == 0) pC.setUsedDate(Date.valueOf(LocalDate.now()));
        return promotionCodeRepository.save(pC);
    }

    public PromotionCode getPromotionCodeById(Long id) {
        return promotionCodeRepository.findById(id).orElse(null);
    }

    public List<PromotionCode> getAllPromotionCode() {
        return promotionCodeRepository.findAll();
    }

    @Transactional
    public int deletePromotionCodesById(Long id) {
        return promotionCodeRepository.deletePromotionCodeById(id);
    }




}







