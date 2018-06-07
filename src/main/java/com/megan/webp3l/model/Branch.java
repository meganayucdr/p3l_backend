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
@Table(name = "TBL_BRANCH")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "branch_id",
            unique = true,
            nullable = false)
    private int id;

    @Column(name = "branch_location",
            unique = true,
            nullable = false,
            length = 20)
    private String location;

    @Column(name = "branch_status",
            nullable = false,
            length = 10)
    private String status = "Active";

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "branch")
    private List<User> userList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "branch")
    private List<Season> seasonList = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "branchList")
    private List<Room> roomList = new ArrayList<>();

}