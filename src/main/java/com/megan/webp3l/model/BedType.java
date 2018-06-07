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
@Table(name = "TBL_BED_TYPE")
public class BedType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bedtype_id",
            unique = true,
            nullable = false)
    private int id;

    @Column(name = "bedtype_name",
            unique = true,
            nullable = false)
    private String name;

    @Column(name = "bedtype_status",
            nullable = false)
    private String status;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bedType")
    private List<Room> roomList = new ArrayList<>();
}
