/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Users;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RISHAV DUTTA
 */
public class LoginServiceTest {
    
//    public LoginServiceTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }

    /**
     * Test of getInstance method, of class LoginService.
     */
//    @Test
//    public void testGetInstance() {
//        System.out.println("getInstance");
//        LoginService expResult = null;
//        LoginService result = LoginService.getInstance();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of doLogin method, of class LoginService.
     */
    @Test
    public void testDoLogin() {
        System.out.println("doLogin");
        Users user = new Users();
        user.setEmail("abc@gmail.com");
        user.setPassword("1234");
        
        boolean expResult = true;
        boolean result = LoginService.getInstance().doLogin(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of doSignUp method, of class LoginService.
     */
//    @Test
//    public void testDoSignUp() {
//        System.out.println("doSignUp");
//        Users user = null;
//        LoginService instance = null;
//        boolean expResult = false;
//        boolean result = instance.doSignUp(user);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getUser method, of class LoginService.
     */
//    @Test
//    public void testGetUser() {
//        System.out.println("getUser");
//        String emailAddress = "";
//        LoginService instance = null;
//        Users expResult = null;
//        Users result = instance.getUser(emailAddress);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
