package pl.akademia.api.bike;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademia.api.exceptions.BikeNotFoundException;
import pl.akademia.api.exceptions.FileTypeNotFoundException;
import pl.akademia.api.exceptions.ResourceNotFoundException;
import pl.akademia.api.file.FileService;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BikeRestController {

  private final BikeService bikeService;
  private final FileService fileService;

  public BikeRestController(BikeService bikeService, FileService fileService) {
    this.bikeService = bikeService;
    this.fileService = fileService;
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
  public ResponseEntity<Integer> allBikesAmount(){
    return new ResponseEntity<>(bikeService.allBikesAmount(), HttpStatus.OK);
  }

  @GetMapping("bikes/getBikeXlsFile")
  public ResponseEntity<?> getBikeXlsFile() throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    Resource resource = fileService.getFile(fileService.createBikeXlsFile());
    if (!resource.exists()){
      throw new ResourceNotFoundException("The resource is unknown");
    }
    File targetFile = new File(resource.getFile().getAbsolutePath());
    String contentType = Files.probeContentType(Paths.get(resource.getURI()));
    if(contentType==null){
      throw new FileTypeNotFoundException("The file type is unknown");
    }
    return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + targetFile.getName() + "\"")
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .body(resource);
  }
}
