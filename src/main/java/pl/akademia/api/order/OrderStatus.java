package pl.akademia.api.order;

public enum OrderStatus {

    PLACED("Placed"),
    CONFIRMED("Confirmed"),
    IN_PROGRESS ("In Progress"),
    SENT ("Sent"),
    CANCELED ("Canceled");

    private String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
