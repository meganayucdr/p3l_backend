package com.megan.webp3l.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeRequest {
    private String name;
    private int capacity;
    private String desc;
    private double price;
    private String pict;
    private List<Integer> facilityList = new ArrayList<>();
    private String status;
}
