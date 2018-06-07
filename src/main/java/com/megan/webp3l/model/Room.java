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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_ROOM")
public class Room {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "room_id",
            unique = true,
            nullable = false)
    private int id;

    @Column(name = "room_code",
            nullable = false,
            length = 20)
    private String code;

    @Column(name = "room_number",
            nullable = false)
    private int number;

    @Column(name = "room_floor",
            nullable = false)
    private int floor;

    @Column(name = "room_status",
            nullable = false,
            length = 20)
    private String status = "Active";

    @Column(name = "smooking_status",
            nullable = false,
            length = 30)
    private String smokingStatus;

    @ManyToOne
    @JoinColumn(name = "bedtype_id")
    private BedType bedType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TBL_BRANCH_ROOM",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id"))
    private List<Branch> branchList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "roomtype_id")
    private RoomType roomType;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private List<ReservationDetail> reservationDetailList = new ArrayList<>();
}
