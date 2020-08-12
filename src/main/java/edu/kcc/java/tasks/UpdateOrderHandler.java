package edu.kcc.java.tasks;

import edu.kcc.java.OrderDataHandler;
import edu.kcc.java.order.Order;
import edu.kcc.java.order.data.OrderDAO;
import edu.kcc.java.order.data.OrderDataException;
import edu.kcc.java.ui.UIUtility;
import java.time.LocalDate;
import java.util.List;

/**
 * Handles updating an existing Order record in the data store. Note that the
 * order number is the unique identifier and cannot be updated through this
 * process.
 *
 * @author Andrew Hanson
 */
public class UpdateOrderHandler implements OrderDataHandler {

    /**
     * This is the starting point for all of the order task handling operations.
     *
     * @param dao
     */
    @Override
    public void handleTask(OrderDAO dao) {

        UIUtility.showSectionTitle("Update an Order");

        try {
            List<Order> orders = dao.getAllOrders();
            // Get the original order from a list
            String[] menuOptions = new String[orders.size()];
            for (int i = 0; i < menuOptions.length; i++) {
                menuOptions[i] = (i + 1) + ") " + orders.get(i).toString();
            }
            String userChoice = UIUtility.showMenuOptions(
                    "Select an Order to Update",
                    "Your Choice:",
                    menuOptions);
            try {
                int actualChoice = Integer.parseInt(userChoice) - 1;
                Order order = orders.get(actualChoice);
                updateOrder(dao, order);
                UIUtility.showMessage("Order updated successfully.");
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                UIUtility.showErrorMessage("No valid Order selected.", false);
            }

            // Get new values for all but the license plate (unique id)
            // Save the new values using the update method of the DAO
        } catch (OrderDataException ex) {
            UIUtility.showErrorMessage(ex.getMessage(), true);
        }

        UIUtility.showMessage("\nUpdate an Order complete.");
        UIUtility.pressEnterToContinue();
    }

    /**
     * Handles the actual update part.
     *
     * @param dao the required data access object
     * @param order the Order to update
     * @throws OrderDataException
     */
    private void updateOrder(OrderDAO dao, Order order) throws OrderDataException {

        String msgUpdatingOrder = "Updating Order: " + order;
        String msgOrderNumber = "Order Number: " + order.getOrderNumber();
        String msgOrderDate = "Order date: " + order.getOrderDate();
        String msgVendorId = "VendorID: " + order.getVendorId();
        
        String current = " (Press Enter to keep the current value):";
        
        String promptEnterOrderNum = "Enter the new order number" + current;
        String promptEnterOrderDate = 
                "Enter the new order date in YYYY-MM-DD format" + current;
        String promptEnterVendorId = "Enter the new vendorId: " + current;

        // Use the copy constructor to get a copy of the order to be updated.
        Order updated = new Order(order);
        UIUtility.showMessage(msgUpdatingOrder);
        UIUtility.showMessage(msgOrderNumber);

        // Ask for a new make.  If blank, keep the current make.
        UIUtility.showMessage(msgOrderNumber);
        String updatedOrderNum = UIUtility.getUserString(promptEnterOrderNum);
        if (!updatedOrderNum.isBlank()) {
            updated.setOrderNumber(updatedOrderNum);
        }

        //Ask for a date. If blank, keep the original
        UIUtility.showMessage(msgOrderDate);
        String updatedOrderDate = UIUtility.getUserString(promptEnterOrderDate);
        if (!updatedOrderDate.isBlank()) {
            updated.setOrderDate(LocalDate.parse(updatedOrderDate));
        }
        
        // Ask for a vendorId.  If blank, keep the current vendor id.
        UIUtility.showMessage(msgVendorId);
        String updatedVendorId = UIUtility.getUserString(promptEnterVendorId);
        if (!updatedVendorId.isBlank()) {
            updated.setVendorId(Integer.parseInt(updatedVendorId));
        }

        // Tell the DAO to make the update permanent
        dao.updateOrder(order, updated);
    }

}
