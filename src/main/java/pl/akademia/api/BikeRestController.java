package pl.akademia.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademia.api.model.Bike;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BikeRestController {

    private final BikeService bikeService;

    public BikeRestController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/bikes")
    public ResponseEntity<List<Bike>> getAllBikes(){
        List<Bike> bikes = bikeService.getAllBikes();
        if (bikes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bikes, HttpStatus.OK);
    }
    @GetMapping("/bikes/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable Long id){
        Bike bike = bikeService.getBikeById(id);
        if(bike == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bike, HttpStatus.OK);
    }

    @PostMapping("/bikes")
    public ResponseEntity<Bike> createBike(@RequestBody Bike bike){
        return new ResponseEntity<>(bikeService.createBike(bike), HttpStatus.CREATED);
    }
    @DeleteMapping("/bikes/{id}")
    public ResponseEntity<Bike> deleteBikeById(@PathVariable Long id){
        Bike bike = bikeService.getBikeById(id);
        if (bike == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
        bikeService.deleteBikeById(id);
        return new ResponseEntity<>(HttpStatus.GONE);
        }
    }
    @DeleteMapping("/bikes/{id}/delete")
    public ResponseEntity<Bike> deleteBikeById2(@PathVariable Long id){
        if (bikeService.deleteBikeById2(id) > 0) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/bikes/{id}")
    public ResponseEntity<Bike> updateBikeById(@PathVariable Long id, @RequestBody Bike bike) {
        try {
            bikeService.updateBikeById(id, bike);
        } catch (BikeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }

    @PatchMapping("bikes/{id}")
    public ResponseEntity<Bike> updateBikePriceById(@PathVariable Long id, @RequestBody BigDecimal price){
        try {
            bikeService.updateBikePriceById(id, price);
        } catch (BikeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }
}
