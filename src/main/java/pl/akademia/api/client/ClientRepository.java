package pl.akademia.api.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

  @Query(value = "select c from Client c where c.email = :email")
  Client getClientByEmail(String email);

  @Modifying
  @Query(value = "delete from Client c where c.id = :id")
  int deleteClientById(Long id);

}
