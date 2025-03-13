package org.example.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Table(name = "admin_users")
@Getter
@Setter
@Entity
@DiscriminatorValue("2")
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