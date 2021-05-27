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
    public ResponseEntity<List<Bike>> getAllBikes(){
        List<Bike> bikes = bikeService.getAllBikes();
        if(bikes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bikes, HttpStatus.OK);
    }

    @GetMapping("/bikes/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable Long id){
        Bike bike = bikeService.getBikeById(id);
        if(bike == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bike, HttpStatus.OK);
    }

    @PostMapping("bikes")
    public ResponseEntity<Bike> createBike(@RequestBody Bike bike){
        return new ResponseEntity<>(bikeService.createBike(bike), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Bike> updateBike(@PathVariable Long id, @RequestBody Bike bike){
        return new ResponseEntity<>(bikeService.updateBike(id, bike), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBike/{id}")
    public ResponseEntity<Bike> deleteBike(@PathVariable Long id){
        return new ResponseEntity<>(bikeService.delete(id), HttpStatus.GONE);
    }

    @PatchMapping("/editBikeType/{id}/{type}")
    public ResponseEntity<Bike> editBike(@PathVariable Long id, @PathVariable String type){
        return new ResponseEntity<>(bikeService.patchBikeType(id,type), HttpStatus.OK);
    }
}
