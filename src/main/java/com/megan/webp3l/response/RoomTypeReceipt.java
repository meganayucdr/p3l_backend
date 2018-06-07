package com.megan.webp3l.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeReceipt {
    String roomType;
    String bedType;
    int quantity;
    double price;
    double subTotal;
    Date checkIn;
    Date checkOut;
}
