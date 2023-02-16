/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Country;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author RISHAV DUTTA
 */
public class CountryService {

    public static ArrayList getAllCountry() {
        ArrayList countryList = new ArrayList();
        try {

            Connection con = JDBCConnectionManager.getConnection();

            String sql = "Select * from country";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Country country = new Country();

                country.setCountryId(rs.getInt("countryId"));
                country.setCountryName(rs.getString("countryName"));

                countryList.add(country);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger log = Logger.getLogger(CountryService.class.getName());
            log.error(LocalDateTime.now() + "-- Error in the getAllCountry Process !!!!!" + " Error Code: " + ex.getErrorCode());
        }
        return countryList;
    }
}
