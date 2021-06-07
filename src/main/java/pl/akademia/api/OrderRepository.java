package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.akademia.api.model.Order;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(nativeQuery = true,value = "select * from orders where order_date= :date")
    List<Order> getOrderByDate(Date date);

    @Query(value = "select o from Order o where size(o.basket.bikes)>= :min and size(o.basket.bikes)<= :max")
    List<Order> getOrderBySize(int min, int max);
}
