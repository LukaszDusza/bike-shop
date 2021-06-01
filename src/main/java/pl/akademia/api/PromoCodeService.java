package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.Order;
import pl.akademia.api.model.PromoCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class PromoCodeService {
    private  final int maxMonthsExp = 6;
    private  final int minMonthsExp = 1;
    private  final BigDecimal maxDiscount = new BigDecimal(500);
    private  final BigDecimal minDiscount = new BigDecimal(50);
    private  final int maxMultipleUse = 20;


    public final PromoCodeRepository promoCodeRepository;

    public PromoCodeService(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }

    public PromoCode createPromoCode(Order order){

        PromoCode promoCode = new PromoCode();
        promoCode.setPromoCode(UUID.randomUUID());
        promoCode.setOrder_id(order.getId());
        promoCode.setGenerateDate(LocalDateTime.now());
        promoCode.setExpDate(promoCode.getGenerateDate().plusMonths(ThreadLocalRandom.current().nextInt(minMonthsExp, maxMonthsExp+1)));
        promoCode.setDiscount(new BigDecimal(ThreadLocalRandom.current().nextInt(minDiscount.intValue(), maxDiscount.intValue())));
        promoCode.setMultipleUse(ThreadLocalRandom.current().nextBoolean());
        if (promoCode.isMultipleUse()){
          promoCode.setMultipleUse(ThreadLocalRandom.current().nextInt(2, maxMultipleUse));
        } else
            promoCode.setMultipleUse(1);
        return promoCodeRepository.save(promoCode);
    }


}
