package com.example.plantio1.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int User_id;

    @Column(nullable = false)
    private String User_name;

    @Column(nullable = false)
    private String User_email;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String User_password;

    @Column(nullable = false)
    private String role;

    public int getId() {
        return User_id;
    }

    public void setId(int id) {
        this.User_id = User_id;
    }

    public String getName() {
        return User_name;
    }

    public void setName(String name) {
        this.User_name = User_name;
    }

    public String getEmail() {
        return User_email;
    }

    public void setEmail(String email) {
        this.User_email = User_email;
    }

    public String getPassword() {
        return User_password;
    }

    public void setPassword(String password) {
        this.User_password = User_password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
