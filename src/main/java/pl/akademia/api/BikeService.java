package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.Bike;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    public Bike createOrUpdateBike(Bike bike){
        return bikeRepository.save(bike);
    }

    public Bike updateBike(Long id, Bike bike){
        return bikeRepository.save(bike);
    }

//    //PUT http method by Beata
//    public Bike updateBike(Long id, Bike bike){
//        Bike bikeEdited = bikeRepository.findById(id).orElse(null);
//          Bike bikeEdited = getBikeById(id); --- albo taki - to od Kamila
//        bikeEdited.setType(bike.getType());
//        bikeEdited.setBrand(bike.getBrand());
//        bikeEdited.setPrice(bike.getPrice());
//        bikeEdited.setStock(bike.getStock());
//        return bikeRepository.save(bikeEdited);
//    }

    @Transactional // to musi być - jeśli chcemy coś skasować, to włacza taki mechanizm w obrębie "transakcj" - dopóki
    // sql nie skasuje całego obiektu, to nie dostaniemy obieku - to zabezpiecza gdy mamy relację między tabelami
    // należało by sk
    //jeśli chcemy mieć pewność że wszystko się powiodła kasujemy za pomocą @Transactional i nie ma żadnych relacji
    // --- to po przerwie 1:00:00 dnia 27.05.2021
    public int deleteBikeById(Long id){ // uwaga tu głupi zwracać void bo wtedy nie wiadomo czy coś się skasowało czy nie
        // można by boolean - ale my wolimy inta - tak jak ma to zaimplemenowany Sprign - czyli jak 0 to nic, jak 1 to skasowałeś 1
        // jak 2 to dwa obiekty
        return bikeRepository.deleteBikeById(id);
    }

    // DELETE http method by Beata
    public Bike delete(Long id){
        Bike bike = getBikeById(id);
        bikeRepository.delete(bike);
        return bike;
    }

    // PATCH http method by Beata - patchem aktualizujemy tylko jedną rzecz - np cene albo type
    // ale ładniej jest to robić przez createAndUpdade, albo napisać własną sql to aktualizacji ceny - to metoda niżej
    public Bike patchBikeType(Long id, String type){
        Bike bikeEdited = bikeRepository.findById(id).orElse(null);
        bikeEdited.setType(type);
        return bikeRepository.save(bikeEdited);
    }

    // chcemy mieć endpoint na podnoszenie zbiorcze ceny rowerów o odpowiednią wartość

    // by Beata
//    @Transactional
//    public List<Bike> increasePrice(BigDecimal amount){
//        for(BigDecimal oldPrice : bikeRepository.prices()) {
//            oldPrice.add(amount);
//        }
//        // azssss problem taki że tej nowej ceny nie zapisuje w bazie
//        return getAllBikes();
//    }

    // by Beata próba 2
    @Transactional
    public List<Bike> increasePrice(BigDecimal amount){
        List<Bike> updatedPrice = getAllBikes();
        for(Bike bike : updatedPrice) {
            BigDecimal oldPrice = bike.getPrice();
            bike.setPrice(oldPrice.add(amount));
            createOrUpdateBike(bike);
        }
        return getAllBikes();
    }

    //by Lukasz
    @Transactional
    public void switchPrice(BigDecimal value){ // nie jest zadowolony że ma tu voida, ale nie był w stanie nic wymyślić na razie
        bikeRepository.switchPrice(value);
    }

    @Transactional
    public double avaragePriceByBrand(String brand){
        return bikeRepository.avaragePriceByBrand(brand);
    }

    //    - średniej cenie rowerów per marka
//- ilosci wszystkich rowerów w magazynie



}
