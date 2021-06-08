package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademia.api.client.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
