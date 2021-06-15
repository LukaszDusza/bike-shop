package pl.akademia.api.report;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademia.api.order.Order;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class SalesRaportController {


    private final SalesRaportService salesRaportService;

    public SalesRaportController(SalesRaportService salesRaportService) {
        this.salesRaportService = salesRaportService;
    }


    @GetMapping("/raport")
    public ResponseEntity<List<Order>> salesRaportDetails(@RequestParam Date dateFrom, @RequestParam Date dateTo){
        List<Order> orderList = salesRaportService.getSalesRaport(dateFrom,dateTo);
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }

    @GetMapping("/takings")
    public ResponseEntity<BigDecimal> takingsInPeriod(@RequestParam Date dateFrom, @RequestParam Date dateTo){
        BigDecimal totalTalkings = salesRaportService.getTalkingsSum(dateFrom,dateTo);
        return new ResponseEntity<> (totalTalkings,HttpStatus.OK);
    }


}
