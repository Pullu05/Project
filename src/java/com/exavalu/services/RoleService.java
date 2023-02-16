/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Role;
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
public class RoleService {

    public static ArrayList getAllRole() {
        ArrayList roleLIst = new ArrayList();
        try {

            Connection con = JDBCConnectionManager.getConnection();

            String sql = "Select * from roles ";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Role role = new Role();

                role.setRoleId(rs.getInt("roleId"));
                role.setRoleName(rs.getString("roleName"));

                roleLIst.add(role);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger log = Logger.getLogger(RoleService.class.getName());
            log.error(LocalDateTime.now() + "-- Error in the getAllRole Process !!!!!" + " Error Code: " + ex.getErrorCode());
        }

        return roleLIst;
    }

}
