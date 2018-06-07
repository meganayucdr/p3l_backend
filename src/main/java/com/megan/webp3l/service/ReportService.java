package com.megan.webp3l.service;

import com.megan.webp3l.response.DefaultResponse;

public interface ReportService {
    DefaultResponse monthlyIncomeReport(String year);
    DefaultResponse numberOfGuests(String year, String month);
    DefaultResponse getYears();
    DefaultResponse getMonths();
    DefaultResponse getYearsFromUser();
    DefaultResponse newCustomer(String year);
    DefaultResponse topFiveCustomer();
    DefaultResponse incomePerBranchPerYears();
}
