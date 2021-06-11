package pl.akademia.api.report;

import org.springframework.stereotype.Service;
import pl.akademia.api.order.Order;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
public class SalesRaportService {


    private final SalesRaportRepository salesRaportRepository;


    public SalesRaportService(SalesRaportRepository salesRaportRepository) {
        this.salesRaportRepository = salesRaportRepository;
    }

    public List<Order> getSalesRaport(String dateFrom, String dateTo) {
        Date dateF = Date.valueOf(dateFrom);
        Date dateT = Date.valueOf(dateTo);
       List<Order> orderList =  salesRaportRepository.findOrdersByDate(dateF,dateT);
        return  orderList;
    }

    public BigDecimal getTalkingsSum(String dateFrom, String dateTo) {
        Date dateF = Date.valueOf(dateFrom);
        Date dateT = Date.valueOf(dateTo);
        BigDecimal totalTalkings = salesRaportRepository.takingsInPeriod(dateF,dateT);
        return totalTalkings;
    }
}
