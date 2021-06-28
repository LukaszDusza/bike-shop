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


    public List<Order> getSalesRaport(Date dateFrom, Date dateTo) {

        return salesRaportRepository.findOrdersByDate(dateFrom,dateTo);
    }

    public BigDecimal getTalkingsSum(Date dateFrom, Date dateTo) {
        BigDecimal totalTalkings = salesRaportRepository.takingsInPeriod(dateFrom,dateTo);
        return totalTalkings;
    }
}
