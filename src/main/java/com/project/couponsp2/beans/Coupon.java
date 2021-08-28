package com.project.couponsp2.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "coupons")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @ToString.Exclude
    @JsonIgnoreProperties("coupon")
    private Company company;

    @Enumerated(EnumType.ORDINAL)
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private Integer amount;
    private Double price;
    private String image;

}
