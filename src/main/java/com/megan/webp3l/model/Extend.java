package com.megan.webp3l.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_EXTEND")
public class Extend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "extend_id",
            nullable = false,
            unique = true)
    private int id;

    @Column(name = "extendstart_date",
            nullable = false,
            columnDefinition = "date")
    private Date startDate;

    @Column(name = "extendend_date",
            nullable = false,
            columnDefinition = "date")
    private Date endDate;

    @Column(name = "extend_subtotal",
            nullable = false)
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "resdet_id")
    private ReservationDetail reservationDetail;
}
