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
@Table(name = "TBL_ROOM_TYPE")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roomtype_id",
            unique = true,
            nullable = false)
    private int id;

    @Column(name = "roomtype_name",
            unique = true,
            nullable = false,
            length = 20)
    private String name;

    @Column(name = "roomtype_capacity",
            nullable = false)
    private int capacity;

    @Column(name = "roomtype_desc",
            nullable = false,
            columnDefinition = "text")
    private String desc;

    @Column(name = "roomtype_price",
            nullable = false)
    private double price;

    @Column(name = "roomtype_pict",
            nullable = false,
            length = 254)
    private String pict;

    @Column(name = "roomtype_status",
            nullable = false,
            length = 30)
    private String status;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomType")
    private List<Room> roomList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomType")
    private List<ReservationDetail> reservationDetails = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TBL_FACILITY_ROOMTYPE",
            joinColumns = @JoinColumn(name = "roomtype_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id"))
    private List<Facility> facilityList = new ArrayList<>();
}
