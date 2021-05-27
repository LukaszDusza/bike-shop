package pl.akademia.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademia.api.model.Bike;

import java.math.BigDecimal;
import java.util.List;

//@Controller // szukal widokow, zwracl pliki stringowe do html
@RestController
@RequestMapping("/api/v1")
public class BikeRestController {

    private final BikeService bikeService;


    public BikeRestController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    //opakowuje odpowiedz z seriwsu , encja odpowiedzi

    @GetMapping("/bikes")
    public ResponseEntity<List<Bike>> getAllBikes() {
        List<Bike> bikes = bikeService.getAllBikes();
        if(bikes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bikes, HttpStatus.OK);
    }

    @GetMapping("bikes/{id}")
    public ResponseEntity<Bike> getBike(@PathVariable long id){
        Bike bike = bikeService.getBikeById(id);
        if(bike==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bike,HttpStatus.FOUND);
    }

    @PostMapping("/bikes")
    public ResponseEntity<Bike> createBike(@RequestBody Bike bike){
            bikeService.createBike(bike);
        return  new ResponseEntity<>(bike,HttpStatus.CREATED);
    }

    @DeleteMapping("bikes/{id}")
    public ResponseEntity<Bike> deleteBikeById(@PathVariable long id){
        bikeService.deleteBike(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("bikes/{id}")
    public ResponseEntity<Bike> updateBikeById(@PathVariable long id,
                                               @RequestParam String type, @RequestParam String brand,
                                               @RequestParam BigDecimal price, @RequestParam int stock){
        bikeService.updateBike(id,type,brand,price,stock);
        return  new ResponseEntity<>(HttpStatus.OK);

    }





}
