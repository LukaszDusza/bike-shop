package pl.akademia.api.bike;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "bike")
public class Bike {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "type", nullable = false, length = 15)
  private String type;

  @Column(name = "brand", nullable = false, length = 20)
  private String brand;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @Column(name = "stock", nullable = false)
  private int stock;
}
