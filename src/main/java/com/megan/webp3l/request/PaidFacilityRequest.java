package com.megan.webp3l.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaidFacilityRequest {
    private String name;
    private double price;
    private String status;
}
