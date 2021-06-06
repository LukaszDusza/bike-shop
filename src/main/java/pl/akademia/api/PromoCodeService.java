package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.PromoCode;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class PromoCodeService {
    private final int maxMonthsExp = 6;
    private final int minMonthsExp = 1;
    private final BigDecimal maxDiscount = new BigDecimal(500);
    private final BigDecimal minDiscount = new BigDecimal(50);
    private final int maxMultipleUse = 20;


    private final PromoCodeRepository promoCodeRepository;

    public PromoCodeService(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }

    public PromoCode createPromoCode(int activeDays, BigDecimal discount) {
        PromoCode promoCode = new PromoCode();
        promoCode.setPromoCode(UUID.randomUUID());
        promoCode.setGenerateDate(Date.valueOf(LocalDate.now()));
        promoCode.setExpDate(Date.valueOf(promoCode.getGenerateDate().toLocalDate().plusDays(activeDays)));
        promoCode.setDiscount(discount);
        promoCode.setMultipleUse(ThreadLocalRandom.current().nextBoolean());
        if (promoCode.isMultipleUse()) {
            promoCode.setUsePromoCodeCounter(ThreadLocalRandom.current().nextInt(2, maxMultipleUse));
        } else
            promoCode.setUsePromoCodeCounter(1);
        return promoCodeRepository.save(promoCode);
    }

    public PromoCode exampleCreatePromoCode() {
        PromoCode promoCode = new PromoCode();
        promoCode.setPromoCode(UUID.randomUUID());
        promoCode.setGenerateDate(Date.valueOf(LocalDate.now()));
        promoCode.setExpDate(Date.valueOf(promoCode.getGenerateDate().toLocalDate().plusMonths(ThreadLocalRandom.current().nextInt(minMonthsExp, maxMonthsExp + 1))));
        promoCode.setDiscount(new BigDecimal(ThreadLocalRandom.current().nextInt(minDiscount.intValue(), maxDiscount.intValue())));
        promoCode.setMultipleUse(ThreadLocalRandom.current().nextBoolean());
        if (promoCode.isMultipleUse()) {
            promoCode.setUsePromoCodeCounter(ThreadLocalRandom.current().nextInt(2, maxMultipleUse));
        } else
            promoCode.setUsePromoCodeCounter(1);
        return promoCodeRepository.save(promoCode);
    }

    public List<Long> getUsedOrders(UUID promoCode) {
        return promoCodeRepository.getUsedOrders(promoCode);
    }

    public List<LocalDate> getUsedDates(UUID promoCode) {
        return promoCodeRepository.getUsedDates(promoCode);
    }

    public List<Long> getUsedClients(UUID promoCode) {
        return promoCodeRepository.getUsedClients(promoCode);
    }

    public List<PromoCode> getUsedPromoCode(UUID promoCode) {
        return promoCodeRepository.getUsedPromoCode(promoCode);
    }

    public PromoCode getPromoCodeByCode(UUID promoCode) {
        return promoCodeRepository.getPromoCodeByCode(promoCode);
    }

    public PromoCode usePromoCode(UUID promoCode) throws WrongPromoCodeException {
        PromoCode pC = getPromoCodeByCode(promoCode);
        if (pC == null) throw new WrongPromoCodeException("Wrong or Used Promo Code");
        pC.setUsePromoCodeCounter(pC.getUsePromoCodeCounter() - 1);
        if (pC.getUsePromoCodeCounter() == 0) pC.setUsedDate(Date.valueOf(LocalDate.now()));
        return promoCodeRepository.save(pC);
    }

    public PromoCode getPromoCodeById(Long id) {
        return promoCodeRepository.findById(id).orElse(null);
    }

    public List<PromoCode> getAllPromoCode() {
        return promoCodeRepository.findAll();
    }

    @Transactional
    public int deletePromoCodesById(Long id) {
        return promoCodeRepository.deletePromoCodeById(id);
    }

    public PromoCode updateExpDatePromoCodeById(Long id, int activeDays) throws WrongPromoCodeException {
        PromoCode promoCode = getPromoCodeById(id);
        if (promoCode == null) throw new WrongPromoCodeException("Wrong Promo Code");

        promoCode.setExpDate(Date.valueOf(promoCode.getGenerateDate().toLocalDate().plusDays(activeDays)));
        return promoCodeRepository.save(promoCode);
    }


}







