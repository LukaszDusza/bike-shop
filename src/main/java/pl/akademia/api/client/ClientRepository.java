package pl.akademia.api.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

  @Query(value = "select  from Client c where c.email = :email")
  Client getClientByEmail(String email);

}
