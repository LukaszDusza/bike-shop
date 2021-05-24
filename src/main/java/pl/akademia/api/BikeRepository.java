package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.akademia.api.model.Bike;

public interface BikeRepository extends JpaRepository<Bike,Long> {

}
