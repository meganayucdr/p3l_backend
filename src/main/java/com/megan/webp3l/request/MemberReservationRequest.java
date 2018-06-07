package com.megan.webp3l.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberReservationRequest {
    private String status;
    private String type;
    private int adult;
    private int child;
    private int branch;
    private int employee_id;
    private int customer_id;
    private Date startDate;
    private Date endDate;
    private List<RoomTypes> roomTypes;
}
