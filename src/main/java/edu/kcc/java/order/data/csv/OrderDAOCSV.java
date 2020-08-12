package edu.kcc.java.order.data.csv;

import edu.kcc.java.order.Order;
import edu.kcc.java.order.data.OrderDAO;
import edu.kcc.java.order.data.OrderDataException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * A CSV-based implementation of the OrderDAO interface.
 *
 * @author Andrew Hanson
 */
public class OrderDAOCSV implements OrderDAO {

    /**
     * This is a cheat. It is the name of the CSV file.
     */
    private static final String FILE_NAME = "orders.csv";

    /**
     * The collection of Order objects from the file.
     */
    private static ArrayList<Order> orders;

    /**
     * Makes sure the ArrayList of Order objects has been filled from the file.
     *
     * @throws OrderDataException
     */
    private void verifyOrderList() throws OrderDataException {
        if(null == orders){
            readFromFile();
        }
    }

    /**
     * Reads data from the CSV file and stores it in the ArrayList.
     *
     * @throws OrderDataException
     */
    private void readFromFile() throws OrderDataException {
        ArrayList<Order> loadList = new ArrayList<>();
        String line;
        String[] fields;
        String orderNumber;
        LocalDate orderDate;
        int vendorId;
        int lineCount = 0;
        try(Scanner in = new Scanner(new File(FILE_NAME))){
            while(in.hasNextLine()){
                lineCount++;
                line = in.nextLine();
                fields = line.split(",");
                orderNumber = fields[0];
                try{
                    orderDate = LocalDate.parse(fields[1]);
                } catch(NumberFormatException nfe){
                    throw new OrderDataException(nfe.getMessage()
                            + "CSV Line " + lineCount);
                }
                try{
                    vendorId = Integer.parseInt(fields[2]);
                } catch(NumberFormatException nfe){
                    throw new OrderDataException(nfe.getMessage()
                            + "CSV Line " + lineCount);
                }
                loadList.add(new Order(orderNumber, orderDate, vendorId));
            }
        } catch(FileNotFoundException fnfe){
            throw new OrderDataException(fnfe);
        }
        orders = loadList;
    }

    /**
     * Iterates through the ArrayList and writes the Order records to the file.
     *
     * @throws OrderDataException
     */
    private void saveToFile() throws OrderDataException {
        try(PrintWriter writer = new PrintWriter(new File(FILE_NAME))){
            String line;
            for (Order order : orders) {
                line = order.getOrderNumber() + ","
                        + order.getOrderDate() + ","
                        + order.getVendorId();
                writer.println(line);
            }
        } catch (FileNotFoundException ex) {
            throw new OrderDataException(ex);
        }
    }


    /**
     * Creates a new Order record based on the values in the supplied Order.
     *
     * @param order the Order object with the record values
     * @throws OrderDataException
     */
    @Override
    public void createOrderRecord(Order order) throws OrderDataException {
        verifyOrderList();
        Order checkOrder = getOrderByOrderNumber(order.getOrderNumber());
        if(null != checkOrder){
            throw new OrderDataException("Order numbers must be unique.");
        }
    
        orders.add(order);
        saveToFile();
    }

     /**
     * Returns the Order record associated with the orderNumber or null if there
     * is no such order.
     *
     * @param orderNumber the identifier of the desired Order record
     * @return the associated Order or null if not found
     * @throws OrderDataException
     */
    @Override
    public Order getOrderByOrderNumber(String orderNumber) 
            throws OrderDataException {
        verifyOrderList();
        Order order = null;
        for (Order order1 : orders) {
            if(order1.getOrderNumber().equals(orderNumber)){
                order = order1;
                break;
            }
        }
        return order;
    }

    /**
     * Returns a list of all the current Order records.
     *
     * @return list of Order records
     * @throws OrderDataException
     */
    @Override
    public List<Order> getAllOrders() throws OrderDataException {
        verifyOrderList();
        List<Order> ordersCopy = new ArrayList<>();
        for (Order order : orders) {
            ordersCopy.add(new Order(order));
        }
        return ordersCopy;
    }

    /**
     * Looks for the original Order and updates it to match the updated Order. If
     * the original Order cannot be found, the method will throw an Exception.
     *
     * @param original The Order record to be updated
     * @param updated The Order containing the updated values
     * @throws OrderDataException
     */
    @Override
    public void updateOrder(Order original, Order updated)
            throws OrderDataException {
        verifyOrderList();
        Order foundOrder = null;
        for (Order order : orders) {
            if(order.equals(original)){
                foundOrder = order;
            }
        }
        if(null == foundOrder){
            throw new OrderDataException("Original record not found for update.");
        }
        
        foundOrder.setOrderNumber(updated.getOrderNumber());
        foundOrder.setOrderDate(updated.getOrderDate());
        foundOrder.setVendorId(updated.getVendorId());
        saveToFile();
    }

    /**
     * Permanently deletes the supplied Order record
     *
     * @param order the Order to delete
     * @throws OrderDataException
     */
    @Override
    public void deleteOrder(Order order) throws OrderDataException {
        deleteOrder(order.getOrderNumber());
    }

    /**
     * Permanently deletes the Order record with the supplied order number value.
     *
     * @param orderNumber the unique identifier for the Order to be deleted
     * @throws OrderDataException
     */
    @Override
    public void deleteOrder(String orderNumber) throws OrderDataException {
        verifyOrderList();
        for (Order order : orders) {
            if(order.getOrderNumber().equals(orderNumber)){
                orders.remove(order);
                break;
            }
        }
    }

}
