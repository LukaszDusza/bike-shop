package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.akademia.api.model.Bike;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

  // @Query(nativeQuery = true, value = "delete from bike where id = ?1") // sql
  @Modifying
  @Query(value = "delete from Bike b where b.id = :id") // jpql
  int deleteBikeById(Long id);

  //@Query(nativeQuery = true, value = "update bike set price = SUM(price + :value )")
  @Modifying
  @Query(value = "update Bike b set b.price = SUM(b.price + :value)")
  void switchPrice(BigDecimal value);

  @Query(value = "select avg(b.price) from Bike b where b.brand = ?1")
  Double averagePriceByBrand(String brand);

  @Query(nativeQuery = true, value = "select count(*) from bike")
  int allBikesAmount();
}
