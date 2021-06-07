package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.Order;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
public class SalesRaportService {


    private final SalesRaportRepository salesRaportRepository;


    public SalesRaportService(SalesRaportRepository salesRaportRepository) {
        this.salesRaportRepository = salesRaportRepository;
    }

    public List<Order> getSalesRaport(Date dateF, Date dateT) {
       List<Order> orderList =  salesRaportRepository.findOrdersByDate(dateF,dateT);
        return  orderList;
    }

    public BigDecimal getTalkingsSum(Date dateF, Date dateT) {
        BigDecimal totalTalkings = salesRaportRepository.takingsInPeriod(dateF,dateT);
        return totalTalkings;
    }
}
