/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;


import com.exavalu.models.Employee;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class EmployeeService {
    
    public static EmployeeService employeeService = null;
    
    public static EmployeeService getInstance()
    {
        if(employeeService==null)
        {
            return new EmployeeService();
        }
        else
        {
            return employeeService;
        }
    }
    
    public ArrayList getAllEmployees()
    {
        ArrayList empList = new ArrayList();
         String sql = "SELECT  id,firstName,lastName,phone,gender,age,departmentName,roleName,salary,specialAllowance,carAllowance FROM newemployees LEFT JOIN roles On newemployees.roleId= roles.roleId LEFT JOIN departments On newemployees.departmentId= departments.departmentId";
        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                Employee emp = new Employee();
                
                emp.setId(rs.getInt("id"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhoneNumber(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getInt("age"));
                emp.setDeptName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setBasicSalary(rs.getDouble("salary"));
                emp.setSpecialAllowance(rs.getDouble("specialAllowance"));
                emp.setCarAllowance(rs.getDouble("carAllowance"));
                
                empList.add(emp);
            }
            
            
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.err.println("Total rows:"+empList.size());
        return empList;
    }
    
     public Boolean AddEmployeeData(Employee emp) {
        boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "INSERT INTO newemployees(firstName,lastName,phone,gender,age,departmentId,roleId,salary,specialAllowance,carAllowance)"
                    + "VALUES(? ,? ,? ,? , ? , ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
                        
            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhoneNumber());
            preparedStatement.setString(4, emp.getGender());
            preparedStatement.setInt(5, emp.getAge());
            preparedStatement.setInt(6, emp.getDepartmentId());
            preparedStatement.setInt(7, emp.getRoleId());
            preparedStatement.setDouble(8, emp.getBasicSalary());
            preparedStatement.setDouble(9, emp.getSpecialAllowance());
            preparedStatement.setDouble(10, emp.getCarAllowance());

            int row = preparedStatement.executeUpdate();

            System.out.println("SQl=" + preparedStatement);
            if (row == 1) {
                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
     
     public ArrayList showSearchEmployeeList(Employee emp1) {
        ArrayList empList = new ArrayList();
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "SELECT  id,firstName,lastName,phone,gender,age,departmentName,roleName,salary,specialAllowance,carAllowance FROM newemployees LEFT JOIN roles On newemployees.roleId= roles.roleId LEFT JOIN departments On newemployees.departmentId= departments.departmentId where firstName like ? \n"
                    + "and lastName like ? and gender like ? and newemployees.departmentId like ? and newemployees.roleId like ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp1.getFirstName() + '%');
            preparedStatement.setString(2, emp1.getLastName() + '%');
            preparedStatement.setString(3, emp1.getGender() + '%');

            if (emp1.getDepartmentId() == 0) {
                preparedStatement.setString(4, "" + '%');
            } else {
                preparedStatement.setString(4, Integer.toString(emp1.getDepartmentId()) + '%');
            }
            if (emp1.getRoleId() == 0) {
                preparedStatement.setString(5, "" + '%');
            } else {
                preparedStatement.setString(5, Integer.toString(emp1.getRoleId()) + '%');
            }

            System.out.println("sql: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Employee emp = new Employee();

                emp.setId(rs.getInt("id"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhoneNumber(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getInt("age"));
                emp.setDeptName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setBasicSalary(rs.getDouble("salary"));
                emp.setSpecialAllowance(rs.getDouble("specialAllowance"));
                emp.setCarAllowance(rs.getDouble("carAllowance"));

                empList.add(emp);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return empList;
    }
     
      public  Employee getEmployee(String employeeId) {
        Employee emp = new Employee();
        try {
            Connection con = JDBCConnectionManager.getConnection();

            String sql = "select * from newemployees e, departments d, roles r " + "where e.departmentId=d.departmentId and e.roleId=r.roleId and e.id=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, employeeId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                emp.setId(rs.getInt("id"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhoneNumber(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getInt("age"));
                emp.setDeptName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setBasicSalary(rs.getDouble("salary"));
                emp.setSpecialAllowance(rs.getDouble("specialAllowance"));
                emp.setCarAllowance(rs.getDouble("carAllowance"));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return emp;
    }
    
     public boolean updateEmployee(Employee emp, String employeeId) {

        boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "UPDATE newemployees "
                    + "SET firstName = ? , lastName = ? , phone = ?,"
                    + "gender = ? , age = ? ,departmentId = ?,roleId = ?, salary = ?,specialAllowance=?,carAllowance = ? "
                    + "WHERE id = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhoneNumber());
            preparedStatement.setString(4, emp.getGender());
            preparedStatement.setInt(5, emp.getAge());
            preparedStatement.setInt(6, emp.getDepartmentId());
            preparedStatement.setInt(7, emp.getRoleId());
            preparedStatement.setDouble(8, emp.getBasicSalary());
            preparedStatement.setDouble(9, emp.getSpecialAllowance());
            preparedStatement.setDouble(10, emp.getCarAllowance());

            preparedStatement.setString(11, employeeId);
            
            System.out.println("SQl=" + preparedStatement);
            int row = preparedStatement.executeUpdate();

            
            if (row == 1) {
                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
