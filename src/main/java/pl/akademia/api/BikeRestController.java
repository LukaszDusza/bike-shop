package pl.akademia.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    // pierwsza wersja
//    @PostMapping("bikes")
//    public ResponseEntity<Bike> createBike(@RequestBody Bike bike){
//        return new ResponseEntity<>(bikeService.createOrUpdateBike(bike), HttpStatus.OK);
//    }


    @PostMapping("bikes")
    public ResponseEntity<Bike> createOrUpdateBike(@RequestBody Bike bike){
        if(bike.getId() == null){
            return new ResponseEntity<>(bikeService.createOrUpdateBike(bike), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(bikeService.createOrUpdateBike(bike), HttpStatus.OK);
    }
//    put by Beata - osatecznie przeniesione wyżej
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Bike> updateBike(@PathVariable Long id, @RequestBody Bike bike){
//        return new ResponseEntity<>(bikeService.updateBike(id, bike), HttpStatus.OK);
//    }

    @DeleteMapping("bikes/{id}/delete")
    public ResponseEntity<?>deleteBikeById(@PathVariable Long id){
        if(bikeService.deleteBikeById(id) > 0){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    // delete by Beata wyżej jest lepsze
//    @DeleteMapping("/deleteBike/{id}")
//    public ResponseEntity<Bike> deleteBike(@PathVariable Long id){
//        return new ResponseEntity<>(bikeService.delete(id), HttpStatus.GONE);
//    }

    @PatchMapping("/editBikeType/{id}/{type}")
    public ResponseEntity<Bike> editBike(@PathVariable Long id, @PathVariable String type){
        return new ResponseEntity<>(bikeService.patchBikeType(id,type), HttpStatus.OK);
    }

    @PostMapping("/bikes/risePrice/{amount}")
    public ResponseEntity<List<Bike>> increasePrice2(@PathVariable BigDecimal amount){
        return new ResponseEntity<List<Bike>>(bikeService.increasePrice(amount), HttpStatus.OK);
    }

    //by Luksaz
    @GetMapping("/bikes/{value}/price}")
    public void switchPrice(@PathVariable BigDecimal value){
        bikeService.switchPrice(value);
    }

    @GetMapping ("bikes/{brand}/averagePriceByBrand/")
    public ResponseEntity<Double> averagePriceByBrand(@PathVariable String brand){
        if(bikeService.allBrands().contains(brand)) {
            return new ResponseEntity<>(bikeService.averagePriceByBrand(brand), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping ("bikes/allBikesAmount")
    public ResponseEntity<Integer> allBikesAmount(){
        return new ResponseEntity<>(bikeService.allBikesAmount(), HttpStatus.OK);
    }
}
