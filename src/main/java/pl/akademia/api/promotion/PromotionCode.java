package pl.akademia.api.promotion;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "promo_code")
public class PromotionCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_code_id")
    private Long promotionCodeId;

    @Column(name = "promotion_code", nullable = false, columnDefinition = "Binary(16)")
    private UUID promotionCode;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "used_date")
    private Date usedDate;

    @Column(name = "generate_date", nullable = false)
    private Date generateDate;

    @Column(name = "exp_date", nullable = false)
    private Date expDate;

    @Column(name = "discount", nullable = false)
    private BigDecimal discount;

    @Column(name = "is_multiple_use", nullable = false)
    private boolean isMultipleUse;

    @Column(name = "multiple_use", nullable = false)
    private int usePromotionCodeCounter;





}
