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
@Table(name = "TBL_RESERVATION_DETAIL")
public class ReservationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "resdet_id",
            unique = true,
            nullable = false)
    private int id;

    @Column(name = "checkin_date",
            nullable = true)
    private Date checkinDate;

    @Column(name = "checkout_date",
            nullable = true)
    private Date checkoutDate;

    @Column(name = "price_perroom",
            nullable = false)
    private double pricePerRoom;

    @Column(name = "deposit",
            nullable = true)
    private double deposit;

    @Column(name = "res_start_date",
            nullable = false,
            columnDefinition = "date")
    private Date startDate;

    @Column(name = "res_end_date",
            nullable = false,
            columnDefinition = "date")
    private Date endDate;

    @Column(name = "resdet_subtotal",
            nullable = false)
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "roomtype_id")
    private RoomType roomType;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservationDetail")
    private List<Extend> extendList = new ArrayList<>();
}
