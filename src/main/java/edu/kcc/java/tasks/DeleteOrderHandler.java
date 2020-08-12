package edu.kcc.java.tasks;

import edu.kcc.java.OrderDataHandler;
import edu.kcc.java.order.Order;
import edu.kcc.java.order.data.OrderDAO;
import edu.kcc.java.order.data.OrderDataException;
import edu.kcc.java.ui.UIUtility;
import java.util.List;

/**
 * Handles the task of deleting a Order object from the data store.
 *
 * @author Andrew Hanson
 */
public class DeleteOrderHandler implements OrderDataHandler {

   /**
     * This is the starting point for all of the order task handling operations.
     * @param dao
     */
    @Override
    public void handleTask(OrderDAO dao) {

        UIUtility.showSectionTitle("Delete an Order");

        try {
            List<Order> orders = dao.getAllOrders();
            String[] menuOptions = new String[orders.size()];
            for (int i = 0; i < menuOptions.length; i++) {
                menuOptions[i] = (i + 1) + ") " + orders.get(i).toString();
            }
            String userChoice = UIUtility.showMenuOptions(
                    "Select an Order to Delete",
                     "Your Choice:",
                     menuOptions);
            try {
                int actualChoice = Integer.parseInt(userChoice) - 1;
                Order order = orders.get(actualChoice);
                dao.deleteOrder(order);
                UIUtility.showMessage("Order deleted successfully.");
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                UIUtility.showErrorMessage("No valid Order selected.", false);
            }

            // Get new values for all but the license plate (unique id)
            // Save the new values using the update method of the DAO
        } catch (OrderDataException ex) {
            UIUtility.showErrorMessage(ex.getMessage(), true);
        }

        UIUtility.showMessage("\nDelete a Order complete.");
        UIUtility.pressEnterToContinue();

    }

}
