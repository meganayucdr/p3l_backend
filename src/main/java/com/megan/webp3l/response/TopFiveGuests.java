package com.megan.webp3l.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopFiveGuests {
    String name;
    int reservationAmount;
    Double total;
}
