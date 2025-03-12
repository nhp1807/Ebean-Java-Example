package org.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "admin_users")
@Getter
@Setter
public class AdminUser extends BaseUser {
    
    @Column(name = "access_level")
    private Integer accessLevel;
    
    @Column(name = "department")
    private String department;
    
    public AdminUser() {
        super();
    }
    
    public AdminUser(String name, String email, Integer accessLevel, String department) {
        super(name, email);
        this.accessLevel = accessLevel;
        this.department = department;
    }
}