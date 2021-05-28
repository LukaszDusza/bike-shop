package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.Bike;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    public Bike getBikeById(Long id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike createBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    public Bike updateBikeById(Bike bike, Bike bikeDetails) {
        bike.setType(bikeDetails.getType());
        bike.setBrand(bikeDetails.getBrand());
        bike.setPrice(bikeDetails.getPrice());
        bike.setStock(bikeDetails.getStock());
        return bikeRepository.save(bike);
    }

    public void deleteBikeById(Long id) {
        bikeRepository.deleteById(id);
    }

    public Bike partialUpdateBike(Bike bike, Map<String, Object> updates) {
        if (updates.containsKey("type")) {
            bike.setType((String) updates.get("type"));
        }
        if (updates.containsKey("brand")) {
            bike.setBrand((String) updates.get("brand"));
        }
        if (updates.containsKey("price")) {
            bike.setPrice(BigDecimal.valueOf((double) updates.get("price")));
        }
        if (updates.containsKey("stock")) {
            bike.setStock((int) updates.get("stock"));
        }
        return bikeRepository.save(bike);
    }
}
