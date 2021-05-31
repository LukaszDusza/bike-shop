package pl.akademia.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademia.api.model.PromoCode;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {



}
