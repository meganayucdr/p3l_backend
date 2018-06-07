package com.megan.webp3l.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_RESERVATION")
public class Reservation {
    @Id
    @Column(name = "reservation_id",
            nullable = false,
            unique = true,
            length = 20)
    private String id;

    @Column(name = "reservation_date",
            nullable = false,
            unique = false)
    private Date date;

    @Column(name = "reservation_status",
            nullable = false,
            length = 30)
    private String status = "Unconfirmed";

    @Column(name = "reservation_type",
            nullable = false,
            length = 20)
    private String type;

    @Column(name = "reservation_total",
            nullable = false)
    private double total;

    @Column(name = "adult",
            nullable = false)
    private int adult;

    @Column(name = "child",
            nullable = false)
    private int child;

    @Column(name = "dp",
            nullable = false)
    private double dp;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee_id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer_id;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
    private List<SpecialDemand> specialDemandList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
    private List<ReservationDetail> reservationDetailList = new ArrayList<>();
}
