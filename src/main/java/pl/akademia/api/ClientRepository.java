package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.akademia.api.model.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(nativeQuery = true, value = "select email from client")
    List<String> getAllEmails();

    @Query(nativeQuery = true, value = "select * from client")
    List<Client> findAllClients();

}
