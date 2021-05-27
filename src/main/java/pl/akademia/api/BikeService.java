package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.Bike;

import java.util.List;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public List<Bike> getAllBikes(){
        return bikeRepository.findAll();
    }

    public Bike getBikeById(Long id){
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike createBike(Bike bike){
        return bikeRepository.save(bike);
    }

    //PUT http method
    public Bike updateBike(Long id, Bike bike){
        Bike bikeEdited = bikeRepository.findById(id).orElse(null);
        bikeEdited.setType(bike.getType());
        bikeEdited.setBrand(bike.getBrand());
        bikeEdited.setPrice(bike.getPrice());
        bikeEdited.setStock(bike.getStock());
        return bikeRepository.save(bikeEdited);
    }

    // DELETE http method
    public Bike delete(Long id){
        Bike bike = getBikeById(id);
        bikeRepository.delete(bike);
        return bike;
    }

    // PATCH http method
    public Bike patchBikeType(Long id, String type){
        Bike bikeEdited = bikeRepository.findById(id).orElse(null);
        bikeEdited.setType(type);
        return bikeRepository.save(bikeEdited);
    }

}
