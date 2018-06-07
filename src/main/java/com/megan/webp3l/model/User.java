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
@Table(name = "TBL_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id",
            unique = true,
            nullable = false)
    private int id;

    @Column(name = "user_name",
            nullable = false,
            length = 64)
    private String name;

    @Column(name = "user_email",
            nullable = false,
            length = 30)
    private String email;

    @Column(name = "user_telp",
            nullable = false,
            length = 20)
    private String telp_numb;

    @Column(name = "user_identity",
            nullable = false,
            length = 20)
    private String identityNumb;

    @Column(name = "user_address",
            nullable = false,
            columnDefinition = "text")
    private String address;

    @Column(name = "user_instance_name",
            nullable = true,
            length = 64)
    private String instance_name;

    @Column(name = "cc_number",
            length = 20)
    private String cc_number;

    @Column(name = "cc_name",
            length = 64)
    private String cc_name;

    @Column(name = "user_password",
            unique = true,
            length = 64)
    private String password;

    @Column(name = "user_status",
            length = 64)
    private String status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer_id")
    private List<Reservation> reservationCustomerList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee_id")
    private List<Reservation> reservationEmployeeList = new ArrayList<>();
}
