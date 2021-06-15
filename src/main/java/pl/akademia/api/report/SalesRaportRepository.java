package pl.akademia.api.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.akademia.api.order.Order;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Repository
public interface SalesRaportRepository extends JpaRepository<Order, Long> {


  @Query(nativeQuery = true,value = "select * from orders where order_date between :dateF and :dateT")
  List<Order> findOrdersByDate(Date dateF, Date dateT);

  @Query(value = "select sum(o.basket.basketTotalPrice) from Order o where o.orderDate between :dateF and :dateT")
  BigDecimal takingsInPeriod(Date dateF, Date dateT);


}
