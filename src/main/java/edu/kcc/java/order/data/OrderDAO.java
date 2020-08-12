package edu.kcc.java.order.data;

import edu.kcc.java.order.Order;
import java.util.List;

/**
 * A Data Access Object interface for working with Order data.
 * 
 * @author Andrew Hanson
 */
public interface OrderDAO {
    
    /**
     * Creates a new Order record based on the values in the supplied Order.
     * 
     * @param orderRecord the Order object with the record values
     * @throws OrderDataException 
     */
    void createOrderRecord(Order orderRecord) throws OrderDataException;
    
    /**
     * Returns the Order record associated with the orderNumber or null if there
     * is no such order.
     * 
     * @param orderNumber the identifier of the desired Order record
     * @return the associated Order or null if not found
     * @throws OrderDataException 
     */
    Order getOrderByOrderNumber(String orderNumber) throws OrderDataException;
    
    /**
     * Returns a list of all the current Order records.
     * 
     * @return list of Order records
     * @throws OrderDataException 
     */
    List<Order> getAllOrders() throws OrderDataException;
    
    
    /**
     * Looks for the original Order and updates it to match the updated Order. 
     * If the original Order cannot be found, the method will throw an 
     * Exception.
     * 
     * @param original The order record to be updated
     * @param updated The Order containing the updated values
     * @throws OrderDataException 
     */
    void updateOrder(Order original, Order updated) throws OrderDataException;
    
    /**
     * Permanently deletes the supplied Order record.
     * 
     * @param order the Order to delete
     * @throws OrderDataException 
     */
    void deleteOrder(Order order) throws OrderDataException;
    
    /**
     * Permanently deletes the Order record with the supplied order number value.
     *
     * @param orderNumber the unique identifier for the Order to be deleted
     * @throws OrderDataException
     */
    void deleteOrder(String orderNumber) throws OrderDataException;
    
        
}
