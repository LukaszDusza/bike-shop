package pl.akademia.api.bike;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class BikeFrontController {

  private BikeService bikeService;

  public BikeFrontController(BikeService bikeService) {
    this.bikeService = bikeService;
  }

  @GetMapping("/bike")
  public String getBikePage(Model model,
                            @RequestParam(required = false) String getAllBikes,
                            @RequestParam(required = false) String getBikeById,
                            @RequestParam(required = false) String getBikeBySerialNumber,
                            @RequestParam(required = false) String info) {
    if (Objects.equals(Integer.toString(1), getAllBikes)) {
      model.addAttribute("bikes", bikeService.getAllBikes());
    }
    if (getBikeById != null) {
      model.addAttribute("bikeById", bikeService.getBikeById(Long.parseLong(getBikeById)));
    }
    if (getBikeBySerialNumber != null) {
      model.addAttribute("bikeBySerialNumber", bikeService.getBikeBySerialNumber(getBikeBySerialNumber));
    }
    if (info != null) {
      model.addAttribute("info", info);
    }
    return "bike";
  }

  @GetMapping("/addBike")
  public String getAddBikePage(Model model, @RequestParam(required = false) String info) {
    model.addAttribute("info", info);
    return "addBike";
  }

  @PostMapping("/add/bike")
  public String addBike(@ModelAttribute Bike bike) {
    bikeService.createOrUpdateBike(bike);
    return "redirect:/addBike?info=Rower zostal zaktualizowany/dodany";
  }

  @GetMapping("/delete/bike")
  public String deleteBike(@RequestParam Long id) {
    bikeService.deleteBikeById(id);
    String info = "Rower o nr id: " + id + " zostal usuniety";
    return "redirect:/bike?info=" + info;
  }

  @GetMapping("/getBikes")
  public String getAllBikes() {
    return "redirect:/bike?getAllBikes=1";
  }

  @GetMapping("/getBikeById")
  public String getBikeById(@RequestParam String id) {
    return "redirect:/bike?getBikeById="+id;
  }

  @GetMapping("/getBikeBySerialNumber")
  public String getBikeBySerialNumber(@RequestParam String serialNumber) {
    return "redirect:/bike?getBikeBySerialNumber="+serialNumber;
  }
}
