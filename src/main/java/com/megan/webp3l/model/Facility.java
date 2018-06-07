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
@Table(name = "TBL_FACILITY")
public class Facility {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "facility_id",
            unique = true,
            nullable = false)
    private int id;

    @Column(name = "facility_name",
            unique = true,
            nullable = false,
            length = 30)
    private String name;

    @Column(name = "facility_status",
            nullable = false,
            length = 20)
    private String status = "Active";

    @JsonIgnore
    @ManyToMany(mappedBy = "facilityList")
    private List<RoomType> roomTypeList = new ArrayList<>();
}
