package pl.akademia.api.model;

public enum UserRole {

    USER_ROLE("user"),
    ADMIN_ROLE("admin");

    private String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
