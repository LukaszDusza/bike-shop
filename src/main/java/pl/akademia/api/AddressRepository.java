package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.akademia.api.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
