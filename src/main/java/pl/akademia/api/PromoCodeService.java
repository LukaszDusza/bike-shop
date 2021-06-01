package pl.akademia.api;

import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import pl.akademia.api.model.Client;
import pl.akademia.api.model.Order;
import pl.akademia.api.model.PromoCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
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
        promoCode.setOrderId(order.getId());
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

    public List<Long> getOrderUsed(PromoCode promoCode){
        List<Long> orders;
        orders = promoCodeRepository.getOrderUsed(promoCode.getPromoCodeId());
        return orders;
    }




}
