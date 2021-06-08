package pl.akademia.api.client;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

    @Data
    @Entity
    @Table(name="address")
    public class Address {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        @Column(name = "postal_code", nullable = false, length = 5)
        private String postalCode;

        @Column(name = "street_name", nullable = false, length = 30)
        private String street;

        @Column(name = "house_number", nullable = false, length = 10)
        private String houseNumber;
    }