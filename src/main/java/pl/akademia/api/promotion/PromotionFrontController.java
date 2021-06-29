package pl.akademia.api.promotion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PromotionFrontController {

    PromotionCodeService promotionCodeService;

    public PromotionFrontController(PromotionCodeService promotionCodeService) {
        this.promotionCodeService = promotionCodeService;
    }

    @GetMapping("/promotionCode")
    public String PromotionCode(Model model){
        return "promotionCode";
    }

    @PostMapping("/promotionCodeCreator")
    public String createPromotionCode(@ModelAttribute PromotionCode promotionCode, Model model){
        PromotionCode generatedCode = promotionCodeService.createPromotionCode(promotionCode.getActiveDays(),promotionCode.getDiscount());
        model.addAttribute("generatedCode", generatedCode.getPromotionCode());
        model.addAttribute("discount", generatedCode.getDiscount());
        model.addAttribute("activeDays", generatedCode.getDiscount());
        return "promotionCode";
    }
}
