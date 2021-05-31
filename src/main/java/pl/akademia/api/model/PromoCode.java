package pl.akademia.api.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "promo_code")
public class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promo_code_id")
    Long id;

    @Column(name = "promo_code")
    UUID promoCode;

    @Column(name = "order_id")
    Long order_id;

    @Column(name = "used_date")
    Date usedDate;

    @Column(name = "generate_date")
    Date generateDate;

    @Column(name = "exp_date")
    Date expDate;

    @Column(name = "discount")
    BigDecimal discount;

    @Column(name = "is_multiple_use")
    boolean isMultipleUse;

    @Column(name = "multiple_use")
    int multipleUse;





}
