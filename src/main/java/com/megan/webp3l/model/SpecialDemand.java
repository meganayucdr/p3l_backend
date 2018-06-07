package com.megan.webp3l.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_SPECIAL_DEMAND")
public class SpecialDemand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "specialdemand_id",
            unique = true,
            nullable = false)
    private int id;

    @Column(name = "specialdemand_item_price",
            nullable = false)
    private double item_price;

    @Column(name = "specialdemand_item_total",
            nullable = false)
    private int item_total;

    @Column(name = "specialdemand_subtotal",
            nullable = false)
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "paidfacility_id")
    private PaidFacility paidFacility;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
