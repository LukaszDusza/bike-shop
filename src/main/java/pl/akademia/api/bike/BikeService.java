package pl.akademia.api.bike;

import org.springframework.stereotype.Service;
import pl.akademia.api.file.FileService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BikeService {

  private final BikeRepository bikeRepository;
  private final FileService fileService;

  public BikeService(BikeRepository bikeRepository, FileService fileService) {
    this.bikeRepository = bikeRepository;
    this.fileService = fileService;
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

  public BigDecimal averagePriceByBrand(String brand){
    return bikeRepository.averagePriceByBrand(brand);
  }

  public int allBikesAmount(){
    return bikeRepository.allBikesAmount();
  }

  public Bike getBikeBySerialNumber(String serialNumber){return bikeRepository.getBikeBySerialNumber(serialNumber);}
}