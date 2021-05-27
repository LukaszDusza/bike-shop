package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.Bike;

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

    public Bike getBikeById(Long id){
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike createBike(Bike bike){
        return bikeRepository.save(bike);
    }

    public void deleteBikeById(Long id){
        bikeRepository.delete(getBikeById(id));
    }

    public Bike updateBikeById(Long id, Bike bike) throws BikeNotFoundException {
        Bike b = getBikeById(id);
        if(b == null) throw new BikeNotFoundException();
        b.setBrand(bike.getBrand());
        b.setPrice(bike.getPrice());
        b.setType(bike.getType());
        b.setStock(bike.getStock());
        return bikeRepository.save(b);
    }

    public Bike updateBikePriceById(Long id, BigDecimal price) throws BikeNotFoundException {
        Bike bike = getBikeById(id);
        if(bike == null) throw new BikeNotFoundException();
        bike.setPrice(price);
        return bikeRepository.save(bike);
    }
}
