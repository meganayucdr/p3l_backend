package com.megan.webp3l.controller;

import com.megan.webp3l.response.DefaultResponse;
import com.megan.webp3l.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService service;

    @RequestMapping(value = "/monthlyIncome", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse monthlyIncomeReport(@Param("year") String year)    {
        return service.monthlyIncomeReport(year);
    }

    @RequestMapping(value = "/numberOfGuests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse numberOfGuestsReport(@Param("year") String year, @Param("month") String month)    {
        return service.numberOfGuests(year, month);
    }

    @RequestMapping(value = "/year", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse getYears()    {
        return service.getYears();
    }

    @RequestMapping(value = "/month/m", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse getMonths()    {
        return service.getMonths();
    }

    @RequestMapping(value = "/user/year/y", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse getYearFromUser()    {
        return service.getYearsFromUser();
    }

    @RequestMapping(value = "/cust/newCust", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse newCustomer(@Param("year") String year)    {
        return service.newCustomer(year);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse incomePerBranchPerYears()    {
        return service.incomePerBranchPerYears();
    }

    @RequestMapping(value = "/cust/top/five/list/report/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DefaultResponse topFiveCust()    {
        return service.topFiveCustomer();
    }
}
