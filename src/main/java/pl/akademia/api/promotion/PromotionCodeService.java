package pl.akademia.api.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger logger = LoggerFactory.getLogger(PromotionCodeService.class);

    private final PromotionCodeRepository promotionCodeRepository;

    public PromotionCodeService(PromotionCodeRepository promotionCodeRepository) {
        this.promotionCodeRepository = promotionCodeRepository;
    }

    public PromotionCode createPromotionCode(int activeDays, BigDecimal discount) {
        PromotionCode promotionCode = new PromotionCode();
        promotionCode.setPromotionCode(UUID.randomUUID().toString());
        promotionCode.setGenerateDate(Date.valueOf(LocalDate.now()));
        promotionCode.setExpDate(Date.valueOf(promotionCode.getGenerateDate().toLocalDate().plusDays(activeDays)));
        promotionCode.setDiscount(discount);
        promotionCode.setMultipleUse(ThreadLocalRandom.current().nextBoolean());
        if (promotionCode.isMultipleUse()) {
            promotionCode.setUsePromotionCodeCounter(ThreadLocalRandom.current().nextInt(2, maxMultipleUse));
            logger.info("Created promotion code with multiple use ({}), with {} discount in zloty", promotionCode.getUsePromotionCodeCounter(), promotionCode.getDiscount());
        } else {
            promotionCode.setUsePromotionCodeCounter(1);
            logger.info("Created promotion code with one use, with {} discount in zloty", promotionCode.getDiscount());
        }
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

    public List<Long> getUsedOrders(String promotionCode) {
        return promotionCodeRepository.getUsedOrders(promotionCode);
    }

    public List<LocalDate> getUsedDates(String promotionCode) {
        return promotionCodeRepository.getUsedDates(promotionCode);
    }

    public List<PromotionCode> getUsedPromotionCode(String promotionCode) {
        return promotionCodeRepository.getUsedPromotionCode(promotionCode);
    }

    public PromotionCode getPromotionCodeByCode(String promotionCode) {
        logger.info("Return promotion code: {}", promotionCode);
        return promotionCodeRepository.getPromotionCodeByCode(promotionCode);
    }

    public List<PromotionCode> getActivePromotionCodeByCode(){
        List<PromotionCode> promotionCodes = promotionCodeRepository.getActivePromotionCodeByCode();
        logger.info("Return {} active promotion codes", promotionCodes.size());
        return promotionCodes;
    }

    public List<PromotionCode> getInactivePromotionCodeByCode(){
        List<PromotionCode> promotionCodes = promotionCodeRepository.getInactivePromotionCodeByCode();
        logger.info("Return {} inactive promotion codes", promotionCodes.size());
        return promotionCodes ;
    }

    public PromotionCode usePromotionCode(String promotionCode) throws WrongPromotionCodeException {
        PromotionCode pC = getPromotionCodeByCode(promotionCode);
        if (pC.getUsedDate() != null) {
            logger.error("Exception: Wrong or Used Promo Code");
            throw new WrongPromotionCodeException("Wrong or Used Promo Code");
        }
        logger.info("Used {} promotion code", promotionCode);
        pC.setUsePromotionCodeCounter(pC.getUsePromotionCodeCounter() - 1);
        if (pC.getUsePromotionCodeCounter() == 0) {
            logger.info("Deactivated {} promotion code", promotionCode);
            pC.setUsedDate(Date.valueOf(LocalDate.now()));
        }
        return promotionCodeRepository.save(pC);
    }

    public PromotionCode getPromotionCodeById(Long id) {
        logger.info("Return promotion code with id {}", id);
        return promotionCodeRepository.findById(id).orElse(null);
    }

    public List<PromotionCode> getAllPromotionCode() {
        List<PromotionCode> promotionCodes = promotionCodeRepository.findAll();
        logger.info("Return {} promotion codes", promotionCodes.size());
        return promotionCodes;
    }

    @Transactional
    public int deletePromotionCodesById(Long id) {
        int deletePromotionCodeById = promotionCodeRepository.deletePromotionCodeById(id);
        logger.info("Deleted {} promotion codes", deletePromotionCodeById);
        return deletePromotionCodeById;
    }




}







