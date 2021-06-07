package pl.akademia.api.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table (name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_method", nullable = false)
    private DeliveryMethod deliveryMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name="informations",length = 500)
    private String additionalInfo;

    @OneToOne
    @JoinColumn (name = "basket_id", nullable = false)
    private Basket basket;

    @OneToOne
    @JoinColumn (name = "client_id",nullable = false)
    private Client client;

    @OneToOne
    @JoinColumn(name = "promo_code_id")
    private PromotionCode promotionCode;

}
