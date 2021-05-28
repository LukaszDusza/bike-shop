package pl.akademia.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.akademia.api.model.Bike;
import pl.akademia.api.model.BikeBrandDTO;


import java.math.BigDecimal;
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

    public Bike getBikeById (long id){
        return bikeRepository.findById(id).orElse(null);  // powinno sie Optional

    }

    public Bike createBike(Bike bike){
        return  bikeRepository.save(bike);

    }

    public void deleteBike(long id) {
        boolean exists = bikeRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Bike with id "+id+ " does not exist");
        }
        bikeRepository.deleteById(id);
    }

    @Transactional
    public void updateBike(long id, String type, String brand, BigDecimal price, int stock) {
       Bike bike = bikeRepository.findById(id).orElseThrow(
               ()-> new IllegalStateException("Bike with id "+id+ " does not exists")); // zwracam Suppliera
        bike.setType(type);
        bike.setBrand(brand);
        bike.setPrice(price);
        bike.setStock(stock);
    }


    public void updateBike2(long id, Bike bike) {
        boolean exists = bikeRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Bike with id "+id+ " does not exist");
        }
        bikeRepository.save(bike);
    }

    @Transactional
    public void update(BikeBrandDTO brandUpdate, long id) {
        Bike bike = bikeRepository.findById(id).orElseThrow(()-> new IllegalStateException("Bike with id "+id+ " does not exist"));
        bike.setBrand(brandUpdate.getBrand());

    }
}
