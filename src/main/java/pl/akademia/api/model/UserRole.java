package pl.akademia.api.model;

public enum UserRole {

    CLIENT_ROLE("client"),
    ADMIN_ROLE("admin");

    private String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
