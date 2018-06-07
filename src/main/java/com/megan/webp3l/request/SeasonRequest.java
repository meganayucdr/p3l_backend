package com.megan.webp3l.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeasonRequest {
    private String name;
    private Date start_date;
    private Date finish_date;
    private double percentage;
    private String status;
    private int branch;
}
