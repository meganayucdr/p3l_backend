package com.megan.webp3l.repository.jdbc;

import com.megan.webp3l.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReportJdbc {
    @Autowired
    private ManageConnection manageConnection;

    public List<IncomePerBranchPerYear> incomePerBranchPerYears()   {
        List<IncomePerBranchPerYear> list = new ArrayList<>();
        String SQL = "SELECT y, IFNULL(SUM(yogyakarta), 0) AS 'yogyakarta', IFNULL(SUM(bandung), 0)  AS 'bandung', " +
                "IFNULL(SUM(yogyakarta), 0) + IFNULL(SUM(bandung), 0) AS 'total'  FROM ( " +
                "SELECT YEAR(RS.reservation_date) AS 'y', SUM(RS.reservation_total) AS 'yogyakarta', " +
                "0 AS 'bandung' " +
                "FROM tbl_reservation RS " +
                "JOIN tbl_branch B ON B.branch_id = RS.branch_id " +
                "WHERE B.branch_location LIKE 'Yogyakarta' AND RS.reservation_status = 'AKTIF' " +
                "GROUP BY B.branch_id " +
                "UNION ALL " +
                "SELECT YEAR(RS.reservation_date) AS 'y' , 0 AS 'yogyakarta', " +
                "SUM(RS.reservation_total) AS 'bandung' " +
                "FROM tbl_reservation RS " +
                "JOIN tbl_branch B ON B.branch_id = RS.branch_id " +
                "WHERE B.branch_location LIKE 'Bandung' AND RS.reservation_status = 'AKTIF' " +
                "GROUP BY B.branch_id ) AS report " +
                "GROUP BY 1";

        try {
            manageConnection.connect();
            PreparedStatement preparedStatement = manageConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())    {
                IncomePerBranchPerYear incomePerBranchPerYear = IncomePerBranchPerYear.builder()
                        .year(resultSet.getString("y"))
                        .branch1(resultSet.getDouble("yogyakarta"))
                        .branch2(resultSet.getDouble("bandung"))
                        .total(resultSet.getDouble("total"))
                        .build();
                list.add(incomePerBranchPerYear);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<NewCustomer> newCustomers(String year) {
        List<NewCustomer> list = new ArrayList<>();
        String SQL = "SELECT MONTHNAME(U.created_at) AS 'Bulan', COUNT(U.user_id) AS 'Jumlah' " +
                "FROM tbl_user U " +
                "WHERE U.role_id >= 6 AND YEAR(U.created_at) = ? " +
                "GROUP BY MONTHNAME(U.created_at)";

        try {
            manageConnection.connect();
            PreparedStatement preparedStatement = manageConnection.connection.prepareStatement(SQL);
            preparedStatement.setString(1, year);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())    {
                NewCustomer newCustomer = NewCustomer.builder()
                        .amount(resultSet.getInt("Jumlah"))
                        .month(resultSet.getString("Bulan"))
                        .build();

                list.add(newCustomer);
            }
            manageConnection.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MonthlyIncome> monthlyIncomes(String year) {
        List<MonthlyIncome> list = new ArrayList<>();
        String SQL = "SELECT month, IFNULL(SUM(p), 0) AS 'p', IFNULL(SUM(g), 0) AS 'g'," +
                "IFNULL(SUM(g), 0) + IFNULL(SUM(p), 0) AS 'total' " +
                "FROM( " +
                "SELECT MONTHNAME(reservation_date) AS 'month', " +
                "SUM(reservation_total) AS 'p', " +
                "0 AS 'g' FROM tbl_reservation " +
                "WHERE SUBSTR(reservation_id, 1, 1) LIKE 'P' AND YEAR(reservation_date) LIKE ? AND reservation_status = 'AKTIF'" +
                "GROUP BY 1 " +
                "UNION ALL " +
                "SELECT MONTHNAME(reservation_date) AS 'month', " +
                "0 AS 'p', " +
                "SUM(reservation_total) AS 'g' " +
                "FROM tbl_reservation " +
                "WHERE SUBSTR(reservation_id, 1, 1) LIKE 'G' AND YEAR(reservation_date) LIKE ? AND reservation_status = 'AKTIF' " +
                "GROUP BY 1) as report " +
                "GROUP BY 1";

        try {
            manageConnection.connect();
            PreparedStatement preparedStatement = manageConnection.connection.prepareStatement(SQL);
            preparedStatement.setString(1, year);
            preparedStatement.setString(2, year);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())    {
                MonthlyIncome monthlyIncome = MonthlyIncome.builder()
                        .month(resultSet.getString("month"))
                        .groupIncome(resultSet.getDouble("g"))
                        .personalIncome(resultSet.getDouble("p"))
                        .total(resultSet.getDouble("total"))
                        .build();

                list.add(monthlyIncome);
            }
            manageConnection.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<NumberOfGuests> numberOfGuests(String year, String month)   {
        List<NumberOfGuests> list = new ArrayList<>();
        String SQL = "SELECT roomType, IFNULL(SUM(g), 0) AS 'g', IFNULL(SUM(p), 0) AS 'p', " +
                "IFNULL(SUM(g), 0) + IFNULL(SUM(p), 0) AS 'total'" +
                "FROM ( " +
                "SELECT RT.roomtype_name AS 'roomType', " +
                "0 AS 'g', " +
                "COUNT(*) AS 'p' " +
                "FROM tbl_reservation_detail RD " +
                "JOIN tbl_room_type RT ON RD.roomtype_id = RT.roomtype_id " +
                "JOIN tbl_reservation RS ON RS.reservation_id = RD.reservation_id " +
                "WHERE SUBSTR(RS.reservation_id, 1, 1) LIKE 'P' AND YEAR(RS.reservation_date) LIKE ? AND RS.reservation_status = 'AKTIF' " +
                "AND MONTHNAME(RS.reservation_date) LIKE ? " +
                "GROUP BY RT.roomtype_name " +
                "UNION ALL " +
                "SELECT RT.roomtype_name AS 'roomType', " +
                "0 AS 'p', " +
                "COUNT(*) AS 'g' " +
                "FROM tbl_reservation_detail RD " +
                "JOIN tbl_room_type RT ON RD.roomtype_id = RT.roomtype_id " +
                "JOIN tbl_reservation RS ON RS.reservation_id = RD.reservation_id " +
                "WHERE SUBSTR(RS.reservation_id, 1, 1) LIKE 'G' AND YEAR(RS.reservation_date) LIKE ? AND RS.reservation_status = 'AKTIF' " +
                "AND MONTHNAME(RS.reservation_date) LIKE ? " +
                "GROUP BY RT.roomtype_name) as report " +
                "GROUP BY 1";

        try {
            manageConnection.connect();
            PreparedStatement preparedStatement = manageConnection.connection.prepareStatement(SQL);
            preparedStatement.setString(1, year);
            preparedStatement.setString(2, month);
            preparedStatement.setString(3, year);
            preparedStatement.setString(4, month);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())    {
                NumberOfGuests numberOfGuests = NumberOfGuests.builder()
                        .roomType(resultSet.getString("roomType"))
                        .groupAmount(resultSet.getInt("g"))
                        .personalAmount(resultSet.getInt("p"))
                        .total(resultSet.getInt("total"))
                        .build();

                list.add(numberOfGuests);
            }
            manageConnection.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TopFiveGuests> topFiveGuests()  {
        List<TopFiveGuests> list = new ArrayList<>();
        String SQL = "SELECT U.user_name AS 'nama', COUNT(*) AS 'BanyakReservasi', " +
                "SUM(RS.reservation_total) AS 'TotalBayar' " +
                "FROM tbl_reservation RS " +
                "JOIN tbl_user U ON U.user_id = RS.customer_id " +
                "GROUP BY U.user_id " +
                "ORDER BY 2 DESC " +
                "LIMIT 5";

        try {
            manageConnection.connect();
            PreparedStatement preparedStatement = manageConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())    {
                TopFiveGuests topFiveGuests = TopFiveGuests.builder()
                        .name(resultSet.getString("nama"))
                        .reservationAmount(resultSet.getInt("BanyakReservasi"))
                        .total(resultSet.getDouble("TotalBayar"))
                        .build();

                list.add(topFiveGuests);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getYear() {
        List<String> years = new ArrayList<>();
        String SQL = "SELECT DISTINCT(YEAR(RS.reservation_date)) AS 'tahun' FROM tbl_reservation RS";

        try {
            manageConnection.connect();
            PreparedStatement preparedStatement = manageConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())    {
                String year = resultSet.getString("tahun");
                years.add(year);
            }
            manageConnection.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return years;
    }

    public List<String> getMonth() {
        List<String> months = new ArrayList<>();
        String SQL = "SELECT DISTINCT(MONTHNAME(RS.reservation_date)) AS 'bulan' FROM tbl_reservation RS";

        try {
            manageConnection.connect();
            PreparedStatement preparedStatement = manageConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())    {
                String month = resultSet.getString("bulan");
                months.add(month);
            }
            manageConnection.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return months;
    }

    public List<String> getYearFromUser()   {
        List<String> years = new ArrayList<>();
        String SQL = "SELECT DISTINCT(YEAR(U.created_at)) AS 'tahun' FROM tbl_user U";

        try {
            manageConnection.connect();
            PreparedStatement preparedStatement = manageConnection.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())    {
                String year = resultSet.getString("tahun");
                years.add(year);
            }
            manageConnection.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return years;
    }
}
