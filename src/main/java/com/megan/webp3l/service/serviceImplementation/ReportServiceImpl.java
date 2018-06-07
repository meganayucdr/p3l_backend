package com.megan.webp3l.service.serviceImplementation;

import com.megan.webp3l.repository.jdbc.ReportJdbc;
import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportJdbc reportJdbc;

    @Override
    public DefaultResponse monthlyIncomeReport(String year) {
        return DefaultResponse.builder()
                .data(reportJdbc.monthlyIncomes(year))
                .build();
    }

    @Override
    public DefaultResponse numberOfGuests(String year, String month) {
        return DefaultResponse.builder()
                .data(reportJdbc.numberOfGuests(year, month))
                .build();
    }

    @Override
    public DefaultResponse getYears() {
        return DefaultResponse.builder()
                .data(reportJdbc.getYear())
                .build();
    }

    @Override
    public DefaultResponse getMonths() {
        return DefaultResponse.builder()
                .data(reportJdbc.getMonth())
                .build();
    }

    @Override
    public DefaultResponse getYearsFromUser() {
        return DefaultResponse.builder()
                .data(reportJdbc.getYearFromUser())
                .build();
    }

    @Override
    public DefaultResponse newCustomer(String year) {
        return DefaultResponse.builder()
                .data(reportJdbc.newCustomers(year))
                .build();
    }

    @Override
    public DefaultResponse topFiveCustomer() {
        return DefaultResponse.builder()
                .data(reportJdbc.topFiveGuests())
                .build();
    }

    @Override
    public DefaultResponse incomePerBranchPerYears() {
        return DefaultResponse.builder()
                .data(reportJdbc.incomePerBranchPerYears())
                .build();
    }
}
