package pl.akademia.api.bike;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.akademia.api.file.FileService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BikeService {

private final static Logger logger = LoggerFactory.getLogger(BikeService.class);

  private final BikeRepository bikeRepository;
  private final FileService fileService;

  public BikeService(BikeRepository bikeRepository, FileService fileService) {
    this.bikeRepository = bikeRepository;
    this.fileService = fileService;
  }

  public List<Bike> getAllBikes() {
    logger.info("try to return all bikes");
    //todo logujemy cala apke
    // INFO - wszystko to co informacyjne i moze pomoc w ocenie działania aplikacji
    // DEBUG - czyli b.szczegółowe logowanie. uzywamy gdy chcemy znalezc
    // blad i logowac doslownie kazdy event naszej aplki
    // WARN = nie jest to bład, ale cos mogloby zadzialac lepiej.
    // TRACE  - kiedy serio chcesz debugowac niskopoziomowo
    // ERROR - wszelkie niespodziewane/spodziewane bledy, np. bloki catch, ify, cos co spowodowalo blad apki.
    List<Bike> bikes = bikeRepository.findAll();
    logger.debug("Return {} bikes.", bikes.size());
    return bikes;
  }

  public Bike getBikeById(Long id) {
    logger.info("Return bike by id: {}", id);
    return bikeRepository.findById(id).orElse(null);
  }

  public Bike createOrUpdateBike(Bike bike) {
    logger.info("Bike is created");
    return bikeRepository.save(bike);
  }

  @Transactional
  public int deleteBikeById(Long id) {
    logger.info("Bike by id:{} deleted",id);
    return bikeRepository.deleteBikeById(id);
  }

  @Transactional
  public void switchPrice(BigDecimal value) {
    bikeRepository.switchPrice(value);
  }

  public BigDecimal averagePriceByBrand(String brand){
    logger.info("Get average price by brand");
    return bikeRepository.averagePriceByBrand(brand);
  }

  public int allBikesAmount(){
    logger.info("Get amount of all bikes");
    return bikeRepository.allBikesAmount();
  }

  public Bike getBikeBySerialNumber(String serialNumber){
    logger.info("Get bike by serial number:{}"+serialNumber);
    return bikeRepository.getBikeBySerialNumber(serialNumber);
  }
}