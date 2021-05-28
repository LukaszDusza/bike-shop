package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.Bike;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BikeService {

  private final BikeRepository bikeRepository;

  public BikeService(BikeRepository bikeRepository) {
    this.bikeRepository = bikeRepository;
  }

  public List<Bike> getAllBikes() {
    return bikeRepository.findAll();
  }

  public Bike getBikeById(Long id) {
    return bikeRepository.findById(id).orElse(null);
  }

  public Bike createOrUpdateBike(Bike bike) {
    return bikeRepository.save(bike);
  }

  @Transactional
  public int deleteBikeById(Long id) {
    return bikeRepository.deleteBikeById(id);
  }

  @Transactional
  public void switchPrice(BigDecimal value) {
    bikeRepository.switchPrice(value);
  }
}