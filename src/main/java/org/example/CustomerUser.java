package org.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_users")
@Getter
@Setter
public class CustomerUser extends BaseUser {
    
    @Column(name = "customer_number")
    private String customerNumber;
    
    @Column(name = "membership_level")
    private String membershipLevel;
    
    public CustomerUser() {
        super();
    }
    
    public CustomerUser(String name, String email, String customerNumber, String membershipLevel) {
        super(name, email);
        this.customerNumber = customerNumber;
        this.membershipLevel = membershipLevel;
    }
}
