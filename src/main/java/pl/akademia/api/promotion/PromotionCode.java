package pl.akademia.api.promotion;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;


@Data
@Entity
@Table(name = "promotion_code")
public class PromotionCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "promotion_code", nullable = false)
    private String promotionCode;

    @Column(name = "order_id")
    private Long orderId;

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

