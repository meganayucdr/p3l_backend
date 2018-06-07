package com.megan.webp3l.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyIncome {
    String month;
    double groupIncome;
    double personalIncome;
    double total;
}
