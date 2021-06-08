package pl.akademia.api.order;

import java.math.BigDecimal;

public enum DeliveryMethod {

    COURIER_FREE_DHL_DOMESTIC ("Free by DHL_DOM", new BigDecimal(0)),
    COURIER_DHL_DOMESTIC ("DHL Domestic",new BigDecimal(10.99)),
    COURIER_DHL_INTERNATIONAL("DHL International", new BigDecimal(20.99)),
    COURIER_FEDEX_INTERNTIONAL("FEDEX International", new BigDecimal(25.99)),
    PICK_AT_THE_POINT ("At The Point", new BigDecimal(0));

    private String displayName;
    private BigDecimal shipPrice;

    DeliveryMethod(String displayName, BigDecimal shipPrice) {
        this.displayName = displayName;
        this.shipPrice = shipPrice;
    }

    public String getDisplayName() {
        return displayName;
    }

    public BigDecimal getShipPrice() {
        return shipPrice;
    }
}
