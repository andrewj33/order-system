package edu.kcc.java.tasks;

import edu.kcc.java.OrderDataHandler;
import edu.kcc.java.order.Order;
import edu.kcc.java.order.data.OrderDAO;
import edu.kcc.java.order.data.OrderDataException;
import edu.kcc.java.ui.UIUtility;

/**
 * Lets the user find a specific order by order number.
 *
 * @author Andrew Hanson
 */
public class FindAnOrderHandler implements OrderDataHandler{

    /**
     * This is the starting point for all of the order task handling operations.
     *
     * @param dao
     */
    @Override
    public void handleTask(OrderDAO dao) {

        UIUtility.showSectionTitle("Find an Order");

        String prompt = "Enter an order number:";
        String orderNumber = UIUtility.getUserString(prompt);
        UIUtility.showMessage("Searching for order number " + orderNumber
                + "...");
        Order order;
        try{
            order = dao.getOrderByOrderNumber(orderNumber);
            if(null == order){
                UIUtility.showMessage("No order found with order number: "
                        + orderNumber);
            } else {
                UIUtility.showMessage("Retrieved Order: " + order);
            }
        } catch(OrderDataException cde){
            UIUtility.showErrorMessage(cde.getMessage(), true);
        }
        UIUtility.showMessage("\nFind an Order complete.");
        UIUtility.pressEnterToContinue();
    }


    /**
     * This method is used by other methods when they need to lookup a Order to
     * use in that process.
     *
     * @param dao the OrderDAO instance
     * @param orderNumber the order number of the desired order
     * @return the Order associated with the order number, or null if not found
     * @throws edu.kcc.java.order.data.OrderDataException
     */
    public Order getOrderByOrderNumber(OrderDAO dao, String orderNumber)
            throws OrderDataException{
        return dao.getOrderByOrderNumber(orderNumber);
    }

}
