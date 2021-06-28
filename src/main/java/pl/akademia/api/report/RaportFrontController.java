package pl.akademia.api.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RaportFrontController {

    private SalesRaportService salesRaportService;

    @GetMapping("/raports")
    public String getSaleRaport(){
        return "raports";
    }


}
