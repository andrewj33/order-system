/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kcc.java.order;

import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Andrew Hanson
 */
public class OrderTest {
    

    public static final String DEFAULT_ORDER_NUMBER = "UNKNOWN";
    public static final LocalDate DEFAULT_ORDER_DATE = LocalDate.now();
    public static final int DEFAULT_VENDOR_ID = 101;
    
    
    public OrderTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }
    
    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of toString method, of class Order.
     */
    @org.junit.jupiter.api.Test
    public void testToString() {
        System.out.println("toString");
        Order instance = new Order();
        String expResult = "UNKNOWN " + LocalDate.now() + " 101";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderNumber method, of class Order.
     */
    @org.junit.jupiter.api.Test
    public void testGetOrderNumber() {
        System.out.println("getOrderNumber");
        Order instance = new Order();
        String expResult = "UNKNOWN";
        String result = instance.getOrderNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderNumberNull method, of class Order.
     */
    @org.junit.jupiter.api.Test
    public void testSetOrderNumberNull() {
        System.out.println("setOrderNumber to null");
        String orderNumber = null;
        Order instance = new Order();
        String original = instance.getOrderNumber();
        try{
             instance.setOrderNumber(orderNumber);
             fail("Allowed to set orderNumber to null bad value");
         } catch(IllegalArgumentException ex){
             assertEquals(original, instance.getOrderNumber());
         }
    }
    
    /**
     * Test of setOrderNumberBlank method, of class Order.
     */
    @org.junit.jupiter.api.Test
    public void testSetOrderNumberBlank() {
        System.out.println("setOrderNumber to blank bad value");
        String orderNumber = "";
        Order instance = new Order();
        String original = instance.getOrderNumber();
        try{
             instance.setOrderNumber(orderNumber);
             fail("Allowed to set orderNumber to null");
         } catch(IllegalArgumentException ex){
             assertEquals(original, instance.getOrderNumber());
         }
    }
    
    /**
     * Test of setOrderNumberGood method of class Order.
     */
    @org.junit.jupiter.api.Test
    public void testSetOrderNumberGood() {
        System.out.println("setOrderNumber to good String value");
        String orderNumber = "UNKNOWN";
        Order instance = new Order();
        instance.setOrderNumber(orderNumber);
        assertEquals(orderNumber, instance.getOrderNumber());
    }
    

    /**
     * Test of getOrderDate method, of class Order.
     */
    @org.junit.jupiter.api.Test
    public void testGetOrderDate() {
        System.out.println("getOrderDate");
        Order instance = new Order();
        LocalDate expResult = LocalDate.now();
        LocalDate result = instance.getOrderDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderDateGoodNow method, of class Order.
     */
    @org.junit.jupiter.api.Test
    public void testSetOrderDateGoodNow() {
        System.out.println("setOrderDate to good now value");
        LocalDate orderDate = LocalDate.now();
        Order instance = new Order();
        instance.setOrderDate(orderDate);
    }
    
    /**
     * Test of setOrderDateFutureBad method, of class Order.
     */
    @org.junit.jupiter.api.Test
    public void testSetOrderDateNullBad() {
        System.out.println("setOrderDate to null bad value");
        LocalDate orderDate = null;
        Order instance = new Order();
        LocalDate original = instance.getOrderDate();
        try{
             instance.setOrderDate(orderDate);
             fail("Allowed to set orderDate to null");
         } catch(IllegalArgumentException ex){
             assertEquals(original, instance.getOrderDate());
         }
    }
    
    /**
     * Test of setOrderDateFutureBad method, of class Order.
     */
    @org.junit.jupiter.api.Test
    public void testSetOrderDateFutureBad() {
        System.out.println("setOrderDate to future bad value");
        LocalDate orderDate = LocalDate.now().plusDays(1);
        Order instance = new Order();
        LocalDate original = instance.getOrderDate();
        try{
             instance.setOrderDate(orderDate);
             fail("Allowed to set orderDate to future bad value");
         } catch(IllegalArgumentException ex){
             assertEquals(original, instance.getOrderDate());
         }
    }

    /**
     * Test of getVendorId method, of class Order.
     */
    @org.junit.jupiter.api.Test
    public void testGetVendorId() {
        System.out.println("getVendorId");
        Order instance = new Order();
        int expResult = 101;
        int result = instance.getVendorId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVendorIdGood method, of class Order.
     */
    @org.junit.jupiter.api.Test
    public void testSetVendorIdGood() {
        System.out.println("setVendorId good value");
        int vendorId = 12345;
        Order instance = new Order();
        instance.setVendorId(vendorId);
    }
    
    /**
     * Test of setVendorIdBad100 method, of class Order.
     */
    @org.junit.jupiter.api.Test
    public void testSetVendorIdBad100() {
        System.out.println("setVendorId bad 100 value");
        int vendorId = 100;
        Order instance = new Order();
        int original = instance.getVendorId();
        try{
             instance.setVendorId(vendorId);
             fail("Allowed to set orderDate to 100 bad value");
         } catch(IllegalArgumentException ex){
             assertEquals(original, instance.getVendorId());
         }
    }
        
    /**
     * Test of the full constructor.
     */
    @org.junit.jupiter.api.Test
    public void testFullConstructorGood() {
        System.out.println("testConstructor good");
        String orderNumber = DEFAULT_ORDER_NUMBER;
        LocalDate orderDate = DEFAULT_ORDER_DATE;
        int vendorId = DEFAULT_VENDOR_ID;
        Order instance = new Order(orderNumber, orderDate, vendorId);
        assertEquals(orderNumber, instance.getOrderNumber());
        assertEquals(orderDate, instance.getOrderDate());
        assertEquals(vendorId, instance.getVendorId());
    }
    
    /**
     * Test of the full constructor.
     */
    @org.junit.jupiter.api.Test
    public void testConstructorBadOrderNumberNull() {
        System.out.println("constructor orderNumber set to null");
        String orderNumber = null;
        LocalDate orderDate = DEFAULT_ORDER_DATE;
        int vendorId = DEFAULT_VENDOR_ID;
        try {
            Order instance = new Order(orderNumber,orderDate,vendorId);
            fail("Allowed to instantiate with null orderNubmer");
        } catch (IllegalArgumentException ex) {
            // nothing here
        }
    }
    
    /**
     * Test of the full constructor.
     */
    @org.junit.jupiter.api.Test
    public void testConstructorBadOrderNumberBlank() {
        System.out.println("constructor orderNumber set to blank");
        String orderNumber = "";
        LocalDate orderDate = DEFAULT_ORDER_DATE;
        int vendorId = DEFAULT_VENDOR_ID;
        try {
            Order instance = new Order(orderNumber,orderDate,vendorId);
            fail("Allowed to instantiate with blank orderNumber");
        } catch (IllegalArgumentException ex) {
            // nothing here
        }
    }
    
    /**
     * Test of the full constructor.
     */
    @org.junit.jupiter.api.Test
    public void testConstructorBadOrderDateNull() {
        System.out.println("constructor orderDate set to null");
        String orderNumber = DEFAULT_ORDER_NUMBER;
        LocalDate orderDate = null;
        int vendorId = DEFAULT_VENDOR_ID;
        try {
            Order instance = new Order(orderNumber,orderDate,vendorId);
            fail("Allowed to instantiate with null orderNumber");
        } catch (IllegalArgumentException ex) {
            // nothing here
        }
    }
    
    /**
     * Test of the full constructor.
     */
    @org.junit.jupiter.api.Test
    public void testConstructorBadOrderDateFuture() {
        System.out.println("constructor orderNumber set to bad future orderDate");
        String orderNumber = DEFAULT_ORDER_NUMBER;
        LocalDate orderDate = LocalDate.now().plusDays(1);
        int vendorId = DEFAULT_VENDOR_ID;
        try {
            Order instance = new Order(orderNumber,orderDate,vendorId);
            fail("Allowed to instantiate with bad future orderDate");
        } catch (IllegalArgumentException ex) {
            // nothing here
        }
    }
    
    /**
     * Test of the full constructor.
     */
    @org.junit.jupiter.api.Test
    public void testConstructorBadVendorId100() {
        System.out.println("constructor vendorId set to bad 100 value");
        String orderNumber = DEFAULT_ORDER_NUMBER;
        LocalDate orderDate = DEFAULT_ORDER_DATE;
        int vendorId = 100;
        try {
            Order instance = new Order(orderNumber,orderDate,vendorId);
            fail("Allowed to instantiate with bad 100 value vendorId");
        } catch (IllegalArgumentException ex) {
            // nothing here
        }
    }
    
    
}
