package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.akademia.api.model.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike,Long> {


}
