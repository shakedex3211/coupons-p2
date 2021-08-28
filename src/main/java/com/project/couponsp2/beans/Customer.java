package com.project.couponsp2.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;

    @ManyToMany()//cascade = CascadeType.ALL,orphanRemoval = true)
    @Singular
    @ToString.Exclude
    private List<Coupon> coupons = new ArrayList<>();

}
