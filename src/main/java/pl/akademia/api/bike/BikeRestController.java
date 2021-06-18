package pl.akademia.api.bike;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademia.api.exceptions.BikeNotFoundException;
import pl.akademia.api.exceptions.PropertyValueException;

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
  public ResponseEntity<?> getBikes(@RequestParam(required = false, value = "serial") String serialNumber)  {
    if(serialNumber != null) {
      Bike bike = bikeService.getBikeBySerialNumber(serialNumber);
      if (bike == null) {
        throw new BikeNotFoundException("Bike not found");
      }
      return new ResponseEntity<>(bike, HttpStatus.OK);
    }
    List<Bike> bikes = bikeService.getAllBikes();
    if (bikes.isEmpty()) {
      throw new BikeNotFoundException("BikeShop has no any bikes!");
    }
    return new ResponseEntity<>(bikes, HttpStatus.OK);
  }

  @GetMapping("/bikes/{id}")
  public ResponseEntity<Bike> getBikeById(@RequestParam(value="id") Long id) {
    Bike bike = bikeService.getBikeById(id);
    if (bike == null) throw new BikeNotFoundException("Bike by this ID doesn't exist");
    if (bike == null) {
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(bike, HttpStatus.OK);
  }

  @PostMapping("/bikes")
  public ResponseEntity<Bike> createOrUpdateBike(@RequestBody Bike bike) {

   if (bike.getPrice()==null) {
     //return new ResponseEntity<>(bikeService.createOrUpdateBike(bike), HttpStatus.INTERNAL_SERVER_ERROR);
     throw new PropertyValueException("At least one property is null");
   }
      if (bike.getId() == null) {
    return new ResponseEntity<>(bikeService.createOrUpdateBike(bike), HttpStatus.CREATED);
    }
    return new ResponseEntity<>(bikeService.createOrUpdateBike(bike), HttpStatus.OK);

  }

  @DeleteMapping("/bikes/{id}/delete")
  public ResponseEntity<?> deleteBikeById(@PathVariable Long id) {
    if (bikeService.deleteBikeById(id)==0) throw new BikeNotFoundException("Bike by this ID doesn't exist");
    if (bikeService.deleteBikeById(id) > 0) return new ResponseEntity<>(HttpStatus.OK);
    else
      return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }

  @GetMapping("/bikes/{value}/price")
  public void switchPrice(@PathVariable BigDecimal value) {
    bikeService.switchPrice(value);
  }

  @GetMapping("/bikes/{brand}/price/avg")
  public ResponseEntity<BigDecimal> averagePriceByBrand(@PathVariable String brand){
    if (bikeService.averagePriceByBrand(brand) == null)
      throw new BikeNotFoundException("Bike of this brand doesn't exist");
    if(bikeService.averagePriceByBrand(brand) == null){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    return new ResponseEntity<>(bikeService.averagePriceByBrand(brand), HttpStatus.OK);
  }

  @GetMapping("/bikes/stock")
  public ResponseEntity<Integer> allBikesAmount(){
    return new ResponseEntity<>(bikeService.allBikesAmount(), HttpStatus.OK);
  }

}
