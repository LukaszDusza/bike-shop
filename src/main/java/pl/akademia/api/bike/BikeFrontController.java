package pl.akademia.api.bike;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BikeFrontController {

  private BikeService bikeService;

  @GetMapping("/")
  public String getHomePage(Model model) {
    //todo uzupelnic rowery
    return "index";
  }



}
