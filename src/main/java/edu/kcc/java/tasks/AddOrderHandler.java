package edu.kcc.java.tasks;

import edu.kcc.java.OrderDataHandler;
import edu.kcc.java.order.Order;
import edu.kcc.java.order.data.OrderDAO;
import edu.kcc.java.order.data.OrderDataException;
import edu.kcc.java.ui.UIUtility;
import java.time.LocalDate;

/**
 * Handles the task for adding a Order to the data store.
 *
 * @author Andrew Hanson
 */
public class AddOrderHandler implements OrderDataHandler {

    /**
     * This is the starting point for all of the order task handling operations.
     * 
     * @param dao
     */
    @Override
    public void handleTask(OrderDAO dao) {
        UIUtility.showSectionTitle("Add a Order");

        // Start with the default Order.  Use the setters for data validation.
        Order order = new Order();

        // Get order number
        order.setOrderNumber(
                UIUtility.getUserString("Enter the orderNumber:"));
        
        // Get the current date for the created order
        order.setOrderDate(LocalDate.now());
        
        // Get vendorId
        String prompt = "Enter the vendorId:";
        String notAnIntMessage = "That was not a whole number.";
        
        boolean needed = true;
        while(needed){
            try{
                order.setVendorId(UIUtility.getUserInt(prompt, notAnIntMessage));
                needed = false;
            } catch(IllegalArgumentException iae){
                UIUtility.showErrorMessage(iae.getMessage(), true);
            }
        }

        // add the order with the DAO
        try{
            dao.createOrderRecord(order);
            UIUtility.showMessage("Order added: " + order);
        } catch (OrderDataException ex) {
            UIUtility.showErrorMessage(ex.getMessage(), false);
        }

        UIUtility.showMessage("Add Order Complete");
        UIUtility.pressEnterToContinue();
    }

}
