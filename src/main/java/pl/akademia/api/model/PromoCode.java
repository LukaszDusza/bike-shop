package pl.akademia.api.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "promo_code")
public class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promo_code_id")
    Long id;

    @Column(name = "promo_code", nullable = false)
    UUID promoCode;

    @Column(name = "order_id", nullable = false)
    Long order_id;

    @Column(name = "used_date")
    LocalDateTime usedDate;

    @Column(name = "generate_date", nullable = false)
    LocalDateTime generateDate;

    @Column(name = "exp_date", nullable = false)
    LocalDateTime expDate;

    @Column(name = "discount", nullable = false)
    BigDecimal discount;

    @Column(name = "is_multiple_use", nullable = false)
    boolean isMultipleUse;

    @Column(name = "multiple_use", nullable = false)
    int multipleUse;





}
