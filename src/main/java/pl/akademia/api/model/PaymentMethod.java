package pl.akademia.api.model;

public enum PaymentMethod {

    BANK_TRANSFER ("Bank Transfer"),
    CREDIT_CARD ("Credit Card"),
    PAYPAL("PayPal"),
    BLIK("blik"),
    COD ("Cash on Delivery");

    private String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
