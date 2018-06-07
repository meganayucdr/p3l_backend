package com.megan.webp3l.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableRoomType {
    int roomtype_id;
    String roomtype_name;
    int quantity;
    int capacity;
    double price;
    boolean show = false;
    int roomQty = 0;
}
