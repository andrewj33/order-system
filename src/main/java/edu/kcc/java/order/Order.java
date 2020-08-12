package edu.kcc.java.order;

import java.time.LocalDate;

/**
 * An Order Record class for data objects.
 * 
 * @author Andrew Hanson
 */
public class Order {
    
    /**
     * A default value for order number when none is supplied.
     */
    public static final String DEFAULT_ORDER_NUMBER = "UNKNOWN";
    
    /**
     * A default value for order date when none is supplied.
     */
    public static final LocalDate DEFAULT_ORDER_DATE = LocalDate.now();
    
    /**
     * A default value for vendor id when none is supplied.
     */
    public static final int DEFAULT_VENDOR_ID = 101;
    
    /**
     * An Order number.
     */
    private String orderNumber;
    
    /**
     * An order date.
     */
    private LocalDate orderDate;
    
    /**
     * A vendor Id number.
     */
    private int vendorId;
    
    /**
     * The full constructor requires an argument for each attribute.
     * 
     * @param orderNumber
     * @param orderDate
     * @param vendorId 
     */
    public Order(String orderNumber, LocalDate orderDate, int vendorId) {
        validateOrderNumber(orderNumber);
        validateOrderDate(orderDate);
        validateVendorId(vendorId);
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.vendorId = vendorId;
    }
    
    /**
     * The no-argument constructor. This constructor uses predefined values for
     * the attributes.
     */
    public Order() {
        // Call the other constructor, passing the default values.
        this(DEFAULT_ORDER_NUMBER
                , DEFAULT_ORDER_DATE
                , DEFAULT_VENDOR_ID
        );
    }
    
    /**
     * COME BACK TO THIS
     * @param other 
     */
    public Order(Order other) {
        this.orderNumber = new String(other.getOrderNumber().toCharArray());
        this.orderDate = other.getOrderDate();
        this.vendorId = other.getVendorId();
    }
    
    /**
     * The string as {orderNumber} {orderDate} {vendorId}.
     * 
     * @return {orderNumber} {orderDate} {vendorId}
     */
    @Override
    public String toString() {
        return orderNumber + " " + orderDate + " " + vendorId;
    }
    
    /**
     * Accessor for the order number.
     * 
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }
    
    /**
     * Mutator for the order number.
     * 
     * @param orderNumber the order number to set
     */
    public void setOrderNumber(String orderNumber) {
        validateOrderNumber(orderNumber);
        this.orderNumber = orderNumber;
    }
    
    /**
     * Accessor for the order date.
     * 
     * @return the order date
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }
    
    /**
     * Mutator for the order date.
     * 
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(LocalDate orderDate) {
        validateOrderDate(orderDate);
        this.orderDate = orderDate;
    }
    
    /**
     * Accessor for the vendor Id.
     * 
     * @return the vendor Id
     */
    public int getVendorId() {
        return vendorId;
    }
    
    /**
     * Mutator for the vendor Id.
     * 
     * @param vendorId the vendorId to set
     */
    public void setVendorId(int vendorId) {
        validateVendorId(vendorId);
        this.vendorId = vendorId;
    }
    
    
    /**
     * Validates the order number. Cannot be null, and cannot be blank.
     * 
     * @param orderNumber the value to validate
     */
    private void validateOrderNumber(String orderNumber) {
        if("".equals(orderNumber)) {
            throw new IllegalArgumentException(
                    "Order number cannot be blank");
        }
        if(orderNumber == null){
            throw new IllegalArgumentException(
                    "Order number cannot be null");
        }
    }
        
    /**
     * Validates the order date. Cannot be in the future, and cannot be null.
     * 
     * @param orderDate the value to validate
     */
    private void validateOrderDate(LocalDate orderDate) {
        if(orderDate == null){
            throw new IllegalArgumentException(
                    "Order Date cannot be null");
        }
        if(LocalDate.now().isBefore(orderDate)) {
            throw new IllegalArgumentException(
                    "Order Date cannot be in the future");
        } 
    }
    
    /**
     * Validates the order date. Cannot be in the future, and cannot be null.
     * 
     * @param orderDate the value to validate
     */
    private void validateVendorId(int vendorId) {
        if(vendorId <= 100) {
            throw new IllegalArgumentException(
                    "VendorId cannot be less than or equal to 100");
        }
    }
    
    
    
}
