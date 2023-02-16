/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.State;
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
public class StateService {
    public static ArrayList getAllStateAccordingToCountry(int countryId ){
        ArrayList stateList = new ArrayList();
        try {

            Connection con = JDBCConnectionManager.getConnection();

            String sql = "SELECT stateId, stateName from state where countryId = ?";
            
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, countryId);


            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                State state = new State();
                
                state.setStateId(rs.getInt("stateId"));
                state.setStateName(rs.getString("stateName"));
                
                stateList.add(state);
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger log = Logger.getLogger(StateService.class.getName());
            log.error(LocalDateTime.now() + "-- Error in the getAllStateAccordingToCountry Process !!!!!" + " Error Code: " + ex.getErrorCode());
        }
        return stateList;
    }
}
