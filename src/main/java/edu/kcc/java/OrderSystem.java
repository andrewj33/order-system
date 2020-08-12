/**
 * NAME: Andrew Hanson
 * COURSE: Java II 2019FA CRF02
 * DATE: 2019-11-8
 * ASSIGNMENT: CSV_and_DAO
 */

package edu.kcc.java;

import edu.kcc.java.order.data.OrderDAO;
import edu.kcc.java.order.data.OrderDAOFactory;
import edu.kcc.java.tasks.AddOrderHandler;
import edu.kcc.java.tasks.DeleteOrderHandler;
import edu.kcc.java.tasks.FindAnOrderHandler;
import edu.kcc.java.tasks.ShowAllOrdersHandler;
import edu.kcc.java.tasks.UpdateOrderHandler;
import edu.kcc.java.ui.UIUtility;

/**
 * Uses the DAO Pattern to perform CRUD functions on Order Records
 * 
 * @author Andrew Hanson
 */
public class OrderSystem {
    
    /**
     * 
     * @param args 
     */
    
    public static void main(String[] args) {
        
        UIUtility.showMessage("Program starting...");
        
        // Define variables
        String menuTitle = "Main Menu";
        String[] menuOptions = {
            "1) Add an Order",
            "2) Find an Order",
            "3) Show All Order",
            "4) Update an Order",
            "5) Delete an Order",
            "6) Exit"    
        };
        String prompt = "Your choice:";
        String errorMessage = "Invalid option.  Please try again.";
        String userChoice;
        OrderDAO dao = OrderDAOFactory.getInstance().getOrderDAO();

        // Start the primary program logic
        boolean working = true;
        while (working) {
            userChoice = UIUtility.showMenuOptions(menuTitle,
                    prompt, menuOptions);
            switch (userChoice) {
                case "1":
                    new AddOrderHandler().handleTask(dao);
                    break;
                case "2":
                    new FindAnOrderHandler().handleTask(dao);
                    break;
                case "3":
                    new ShowAllOrdersHandler().handleTask(dao);
                    break;
                case "4":
                    new UpdateOrderHandler().handleTask(dao);
                    break;
                case "5":
                    new DeleteOrderHandler().handleTask(dao);
                    break;
                case "6":
                    working = false;
                    break;
                default:
                    UIUtility.showErrorMessage(errorMessage, true);
            }
        }
        UIUtility.showMessage("\nProgram complete.");
    }

}
