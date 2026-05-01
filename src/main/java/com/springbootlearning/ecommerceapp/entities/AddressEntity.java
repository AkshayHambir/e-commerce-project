package com.springbootlearning.ecommerceapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {

    @Column(name = "address_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String street;

    private String buildingName;

    private String city;

    private String state;

    private String pinCode;

    private String country;

    @ManyToMany(mappedBy = "addresses")
    private List<UserEntity> users;

    public AddressEntity(String street, String buildingName, String city, String state, String pinCode, String country) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.country = country;
    }
}
