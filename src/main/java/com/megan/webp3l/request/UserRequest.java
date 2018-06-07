package com.megan.webp3l.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String name;
    private String email;
    private String telp_numb;
    private String identityNumb;
    private String address;
    private String instance_name;
    private String cc_number;
    private String cc_name;
    private String password;
    private String status;
    private int role;
    private int branch;
}
