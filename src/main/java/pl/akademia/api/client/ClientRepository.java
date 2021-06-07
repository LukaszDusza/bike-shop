package pl.akademia.api.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.akademia.api.model.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

  @Query(value = "select c.id, c.name, c.lastName, c.email, " +
      "c.phoneNumber, c.address from Client c where c.email = :email")
  Client getClientByEmail(String email);

  @Query(nativeQuery = true, value = "select email from client")
  List<String> getAllEmails();

  @Modifying
  @Query(value = "delete from Client c where c.id = :id")
  int deleteClientById(Long id);

}
