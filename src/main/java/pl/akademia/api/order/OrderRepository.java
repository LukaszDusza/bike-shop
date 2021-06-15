package pl.akademia.api.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(nativeQuery = true,value = "select * from orders where order_date= :date")
    List<Order> getOrderByDate(Date date);

    @Query(value = "select o from Order o where size(o.basket.bikes)>= :min and size(o.basket.bikes)<= :max")
    List<Order> getOrderBySize(Integer min, Integer max);

    @Query(nativeQuery =true, value= "SELECT order_date, order_status, informations, delivery_metod, paymment_method FROM orders, client  where orders.client_id=client.id_client and orders.client_id= :id")
    List<Order>  getOrderByClientId(Long id);

    @Query(nativeQuery =true, value= "SELECT order_date, order_status, informations, delivery_metod," +
            " paymment_method FROM orders, bike  where orders.basket_id=bike.id and bike.brand= :brand")
    List<Order>  getOrderByBikeBrand(String brand);


}
