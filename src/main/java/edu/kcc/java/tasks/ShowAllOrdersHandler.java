package edu.kcc.java.tasks;

import edu.kcc.java.OrderDataHandler;
import edu.kcc.java.order.Order;
import edu.kcc.java.order.data.OrderDAO;
import edu.kcc.java.order.data.OrderDataException;
import edu.kcc.java.ui.UIUtility;
import java.util.List;

/**
 * Shows all orders in the data store.
 * @author Andrew Hanson
 */
public class ShowAllOrdersHandler implements OrderDataHandler {

    /**
     * This is the starting point for all of the order task handling operations.
     *
     * @param dao
     */
    @Override
    public void handleTask(OrderDAO dao) {
        UIUtility.showSectionTitle("Show All Orders");

        try {
            List<Order> orders = dao.getAllOrders();
            for (Order order : orders) {
                UIUtility.showMessage("\t" + order);
            }
        } catch (OrderDataException ex) {
            UIUtility.showErrorMessage(ex.getMessage(), true);
        }

        UIUtility.showMessage("\nShow All Orders complete.");
        UIUtility.pressEnterToContinue();

    }

    /**
     *
     * @param dao
     * @return
     * @throws OrderDataException
     */
    public List<Order> getAllOrders(OrderDAO dao) throws OrderDataException{
        return dao.getAllOrders();
    }
}
