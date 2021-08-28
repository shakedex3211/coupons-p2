package com.project.couponsp2.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="companies")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @Singular
    @ToString.Exclude
    @JsonIgnoreProperties("company")
    private List<Coupon> coupons = new ArrayList<>();
}
