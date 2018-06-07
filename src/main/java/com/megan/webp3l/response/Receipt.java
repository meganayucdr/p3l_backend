package com.megan.webp3l.response;

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
public class Receipt {
    String reservation_id;
    String name;
    String address;
    Date date;
    Date checkIn;
    Date checkOut;
    int adult;
    int child;
    List<RoomTypeReceipt> roomTypeReceiptList;
}
