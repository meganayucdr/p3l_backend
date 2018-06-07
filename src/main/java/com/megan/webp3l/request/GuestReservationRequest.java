package com.megan.webp3l.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestReservationRequest {
    private String status;
    private String type;
    private int adult;
    private int child;
    private int branch;
    private int employee_id;
    private Date startDate;
    private Date endDate;
    private String name;
    private String email;
    private String telp_numb;
    private String identityNumb;
    private String address;
    private String instance_name;
    private String cc_number;
    private String cc_name;
    private List<RoomTypes> roomTypes;
}
