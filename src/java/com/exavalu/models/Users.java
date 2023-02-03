/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.EmployeeService;
import com.exavalu.services.LoginService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author RISHAV DUTTA
 */
public class Users extends ActionSupport implements ApplicationAware, SessionAware, Serializable {
    
    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
    }

    
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    
    
    
    public String doLogin() throws Exception {
        sessionMap.clear();
        String result = "FAILURE";

        boolean success = LoginService.getInstance().doLogin(this);
        System.out.println(success);
        if (success) {
            System.out.println("returning Success from doLogin method");
            sessionMap.put("LoggedIn", this);
            
            Users user = LoginService.getInstance().getUser(this.email);
            sessionMap.put("USER", user);
            
            
            ArrayList empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpList", empList);
            result = "SUCCESS";
        } else {
            String errorMsg ="Either Email Address or Password is Wrong";
            sessionMap.put("ErrorMsg", errorMsg);
            System.out.println("returning Failure from doLogin method");
        }
        return result;
    }

    public String doLogout() throws Exception {
        sessionMap.clear();
        return "SUCCESS";
    }

     public String doSignUp() throws Exception{
         sessionMap.clear();
         String result ="FAILURE";
         
         boolean success=LoginService.getInstance().doSignUp(this);
         
         if (success) {
            System.out.println("returning Success from doSignUp method");
            String successMsg ="Now Login with your Email Id and PassWord";
            sessionMap.put("SuccessMsg", successMsg);
            result = "SUCCESS";
        } else {
            String errorMsg ="Email Id Already Register";
            sessionMap.put("ErrorMsg1", errorMsg);
            System.out.println("returning Failure from doSignUp method");
        }
         
         return result;
     }
    
    
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
