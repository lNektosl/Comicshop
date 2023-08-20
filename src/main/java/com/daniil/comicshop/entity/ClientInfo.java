package com.daniil.comicshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "client_info")
public class ClientInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name ="given_name")
    private String name;
    @Column(name ="family_name")
    private String familyName;
    @Column(name = "email")
    private String email;
    @Column(name ="street_address")
    private String address;
    @Column(name ="city")
    private String city;
    @Column(name ="postal_code")
    private String postalCode;
    @Column(name ="country")
    private String county;
    @Column(name ="phone")
    private String phone;

}
