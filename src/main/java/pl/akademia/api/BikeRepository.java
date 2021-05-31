package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.akademia.api.model.Bike;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

//    @Query(nativeQuery = true, value = "delete from bike where id = ?1") /sql "?1 - oznacza pierwszy parametr w metodzie - czyli u nas id
    // to jest natywne querry - czyli zwykłe zapytanie - tj querry, domyślnie jest ustawione gueryy javove czyli jpql
    @Modifying // ona pozwala skasować wiersz - to trzeba dać zawsze jak kazujemy - ona robi tzn. flasch - czyli kasuje i kommituje
//    @Transactional - ???? - daliśmy modyfying zamiast Transactional
    @Query(value = "delete from Bike b where b.id = ?1") //jpql - to jest java persistence querry language- to jest javove sql
//    @Query(value = "delete from Bike b where b.id = :id")  // -- to samo co wyżej
    int deleteBikeById(Long id);

    //by Beata
    @Query(nativeQuery = true, value = "select price from bike")
    List<BigDecimal> prices();

//    @Query(nativeQuery = true, value = "update bike set price = SUM(price + :value)") - tu coś nie działa
    @Modifying
    @Query(value = "update Bike b set b.price = b.price + :value")
    void switchPrice(BigDecimal value);

//    - średniej cenie rowerów per marka
//- ilosci wszystkich rowerów w magazynie

    @Modifying
    @Query(nativeQuery = true, value = "select avg(price) from bike where brand = ?1")
    double avaragePriceByBrand(String brand);



}
