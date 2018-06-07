package com.megan.webp3l.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_PAID_FACILITY")
public class PaidFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "paidfacility_id",
            unique = true,
            nullable = false)
    private int id;

    @Column(name = "paidfacility_name",
            unique = true,
            nullable = false,
            length = 30)
    private String name;

    @Column(name = "paidfacility_price",
            unique = false,
            nullable = false)
    private double price;

    @Column(name = "paidfacility_status",
            nullable = false)
    private String status = "Active";

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paidFacility")
    private List<SpecialDemand> specialDemandList = new ArrayList<>();
}
