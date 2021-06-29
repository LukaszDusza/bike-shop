package pl.akademia.api.bike;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BikeFrontController {

  private BikeService bikeService;

  public BikeFrontController(BikeService bikeService) {
    this.bikeService = bikeService;
  }

  @GetMapping("/bike")
  public String getBikePage(Model model, @RequestParam(required = false) String info) {
    if (info != null) {
      model.addAttribute("info", info);
    }
    return "bike";
  }

  @PostMapping("/add/bike")
  public String addBike(@ModelAttribute Bike bike) {
    bikeService.createOrUpdateBike(bike);
    return "redirect:/bike?info=Rower zostal zaktualizowany/dodany";
  }
}
