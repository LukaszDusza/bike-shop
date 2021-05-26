package pl.akademia.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademia.api.model.Bike;

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
        if(bikes.isEmpty()) {
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
    public ResponseEntity<Bike> createBike(@RequestBody Bike bike) {
        return new ResponseEntity<>(bikeService.createBike(bike), HttpStatus.CREATED);
    }
}
