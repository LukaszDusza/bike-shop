package pl.akademia.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademia.api.model.Bike;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BikeRestController {

  private final BikeService bikeService;

  public BikeRestController(BikeService bikeService) {
    this.bikeService = bikeService;
  }

  @GetMapping("/bikes")
  public ResponseEntity<List<Bike>> getAllBikes() {
    List<Bike> bikes = bikeService.getAllBikes();
    if (bikes.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(bikes, HttpStatus.OK);
  }

  @GetMapping("/bikes/{id}")
  public ResponseEntity<Bike> getBikeById(@PathVariable Long id) {
    Bike bike = bikeService.getBikeById(id);
    if (bike == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(bike, HttpStatus.OK);
  }

  @PostMapping("/bikes")
  public ResponseEntity<Bike> createOrUpdateBike(@RequestBody Bike bike) {
    if (bike.getId() == null) {
      return new ResponseEntity<>(bikeService.createOrUpdateBike(bike), HttpStatus.CREATED);
    }
    return new ResponseEntity<>(bikeService.createOrUpdateBike(bike), HttpStatus.OK);
  }

  @DeleteMapping("/bikes/{id}/delete")
  public ResponseEntity<?> deleteBikeById(@PathVariable Long id) {
    if (bikeService.deleteBikeById(id) > 0) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
  }
  @GetMapping("/bikes/{value}/price")
  public void switchPrice(@PathVariable BigDecimal value) {
    bikeService.switchPrice(value);
  }

  @GetMapping("/bikes/{brand}/price/avg")
  public ResponseEntity<BigDecimal> averagePriceByBrand(@PathVariable String brand){
    if(bikeService.averagePriceByBrand(brand) == null){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    return new ResponseEntity<>(bikeService.averagePriceByBrand(brand), HttpStatus.OK);
  }

  @GetMapping("/bikes/stock")
  public ResponseEntity<Bike> allBikesAmount(){
    return new ResponseEntity(bikeService.allBikesAmount(), HttpStatus.OK);
  }

}
