package pl.akademia.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class BikeRestController {

    private final BikeService bikeService;

    public BikeRestController(BikeService bikeService) {
        this.bikeService = bikeService;
    }
}
