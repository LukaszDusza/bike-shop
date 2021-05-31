package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.Bike;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  @Transactional
  public Double averagePriceByBrand(String brand){
    return bikeRepository.averagePriceByBrand(brand);
  }

  public Set<String> allBrands(){
    Set<String> brands = new HashSet<>();
    for(Bike b : bikeRepository.findAll()){
      brands.add(b.getBrand());
    }
    return brands;
  }

  @Transactional
  public int allBikesAmount(){
    return bikeRepository.allBikesAmount();
  }
}