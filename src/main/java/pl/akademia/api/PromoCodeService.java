package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.PromoCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.ObjLongConsumer;


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

    public PromoCode createPromoCode(){
        PromoCode promoCode = new PromoCode();
        promoCode.setPromoCode(UUID.randomUUID());
        promoCode.setGenerateDate(LocalDateTime.now());
        promoCode.setExpDate(promoCode.getGenerateDate().plusMonths(ThreadLocalRandom.current().nextInt(minMonthsExp, maxMonthsExp+1)));
        promoCode.setDiscount(new BigDecimal(ThreadLocalRandom.current().nextInt(minDiscount.intValue(), maxDiscount.intValue())));
        promoCode.setMultipleUse(ThreadLocalRandom.current().nextBoolean());
        if (promoCode.isMultipleUse()){
          promoCode.setUsePromoCodeCounter(ThreadLocalRandom.current().nextInt(2, maxMultipleUse));
        } else
            promoCode.setUsePromoCodeCounter(1);
        return promoCodeRepository.save(promoCode);
    }

    public List<Long> getUsedOrders(UUID promoCode){
        return promoCodeRepository.getUsedOrders(promoCode);
    }

    public List<LocalDate> getUsedDates(UUID promoCode){
        return promoCodeRepository.getUsedDates(promoCode);
    }

    public List<Long> getUsedClients(UUID promoCode){
        return promoCodeRepository.getUsedClients(promoCode);
    }

    public List<Object[]> getUsedPromoCode(UUID promoCode){
        return promoCodeRepository.getUsedPromoCode(promoCode);
    }






}
