package com.megan.webp3l.repository.jdbc;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ManageConnection {
    Connection connection = null;
    public void connect()   {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://192.168.19.140:3306/8240",
                    "pp8240", "8240");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect()    {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
