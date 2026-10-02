package model;

// RUBRIC: Inheritance
// Admin class User class ko extend kar rahi hai
public class Admin extends User {

    private String adminLevel;

    // Default constructor
    public Admin() {
        super();
    }

    // Constructor
    public Admin(int id, String username, String password,
                 String role, String adminLevel) {

        super(id, username, password, role);

        this.adminLevel = adminLevel;
    }

    // Getter
    public String getAdminLevel() {
        return adminLevel;
    }

    // Setter
    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", role='" + getRole() + '\'' +
                ", adminLevel='" + adminLevel + '\'' +
                '}';
    }
}