/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Distict;
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
public class DistictService {
     public static ArrayList getAllDistictAccordingToState(int stateid ){
        ArrayList distictList = new ArrayList();
        try {

            Connection con = JDBCConnectionManager.getConnection();

            String sql = "SELECT distictId, distictName from distict where stateId =?";
            
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, stateid);


            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Distict distict = new Distict();
                
                distict.setDistictId(rs.getInt("distictId"));
                distict.setDistictName(rs.getString("distictName"));
                
                distictList.add(distict);
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger log = Logger.getLogger(DistictService.class.getName());
            log.error(LocalDateTime.now() + "-- Error in the getAllDistictAccordingToState Process !!!!!" + " Error Code: " + ex.getErrorCode());
        }
        return distictList;
    }
}
