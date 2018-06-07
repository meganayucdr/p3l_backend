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
@Table(name = "TBL_SEASON")
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "season_id",
            unique = true,
            nullable = false)
    private int id;

    @Column(name = "season_name",
            nullable = false,
            length = 30)
    private String name;

    @Column(name = "season_start_date",
            nullable = false,
            columnDefinition = "date")
    private Date start_date;

    @Column(name = "season_finish_date",
            nullable = false,
            columnDefinition = "date")
    private Date finish_date;

    @Column(name = "season_percentage",
            nullable = false)
    private double percentage;

    @Column(name = "season_status",
            nullable = false,
            length = 10)
    private String status = "Active";

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
