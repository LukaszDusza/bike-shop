package pl.akademia.api.user;

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
