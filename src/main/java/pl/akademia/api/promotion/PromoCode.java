package pl.akademia.api.promotion;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "promo_code")
public class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promo_code_id")
    Long promoCodeId;

    @Column(name = "promo_code", nullable = false)
    UUID promoCode;

    @Column(name = "order_id")
    Long orderId;

    @Column(name = "client_id")
    Long clientId;

    @Column(name = "used_date")
    private
    Date usedDate;

    @Column(name = "generate_date", nullable = false)
    Date generateDate;

    @Column(name = "exp_date", nullable = false)
    Date expDate;

    @Column(name = "discount", nullable = false)
    BigDecimal discount;

    @Column(name = "is_multiple_use", nullable = false)
    boolean isMultipleUse;

    @Column(name = "multiple_use", nullable = false)
    int usePromoCodeCounter;





}
