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
public class RoomRequest {
    private int number;
    private int floor;
    private String smokingStatus;
    private int bedType;
    private List<Integer> branchList = new ArrayList<>();
    private int roomType;
    private String status;
}
