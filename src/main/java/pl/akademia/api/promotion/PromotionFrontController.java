package pl.akademia.api.promotion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PromotionFrontController {

    PromotionCodeService promotionCodeService;

    public PromotionFrontController(PromotionCodeService promotionCodeService) {
        this.promotionCodeService = promotionCodeService;
    }

    @GetMapping("/promotionCode")
    public String PromotionCode(@RequestParam(required = false) String code, Model model){
        model.addAttribute("code", code);
        return "promotionCode";
    }

    @PostMapping("/promotionCodeCreator")
    public String createPromotionCode(@ModelAttribute PromotionCode promotionCode, Model model){
        PromotionCode generatedCode = promotionCodeService.createPromotionCode(promotionCode.getActiveDays(),promotionCode.getDiscount());
        model.addAttribute("generatedCode", generatedCode.getPromotionCode());
        model.addAttribute("discount", generatedCode.getDiscount());
        model.addAttribute("activeDays", generatedCode.getActiveDays());
        return "redirect:/promotionCode?code=" + generatedCode.getPromotionCode();
    }
}
