package pl.akademia.api.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.akademia.api.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

  @Query(value = "select c.id, c.name, c.lastName, c.email, " +
      "c.phoneNumber, c.address from Client c where c.email = :email")
  Client getClientByEmail(String email);

}
