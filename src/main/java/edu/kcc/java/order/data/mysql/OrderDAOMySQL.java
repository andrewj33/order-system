package edu.kcc.java.order.data.mysql;

import edu.kcc.java.order.Order;
import edu.kcc.java.order.data.OrderDAO;
import edu.kcc.java.order.data.OrderDataException;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A MySQL implementation of the OrderDAO interface.
 *
 * @author Andrew Hanson
 */
public class OrderDAOMySQL implements OrderDAO {

    /**
     * The default host is localhost (127.0.0.1)
     */
    public static final String DEFAULT_URL = "localhost";

    /**
     * The default port for MySQL is 3306
     */
    public static final int DEFAULT_PORT = 3306;

    private Connection buildConnection() throws SQLException {
        String databaseUrl = "localhost";
        int databasePort = 3306;
        String databaseName = "JDBCDAOAssignment";
        String userName ="SQLDAOUser";
        String password = "week_password";

        /**
         * Build the connection string to be used for the connection
         */
        String connectionString = "jdbc:mysql://" + databaseUrl + ":"
                + databasePort
                + "/" + databaseName
                + "?useSSL=false"
                + "&allowPublicKeyRetrieval=true"
                + "&user=" + userName + "&password="
                + password;

        return DriverManager.getConnection(connectionString);
    }

    /**
     * Creates a new Order record based on the values in the supplied Order.
     *
     * @param order the Order object with the record values
     * @throws OrderDataException
     */
    @Override
    public void createOrderRecord(Order order) throws OrderDataException {
        try{
            Connection conn = buildConnection();
            CallableStatement callableStatement
                    = conn.prepareCall("CALL sp_add_Order(?,?,?,?);");
            callableStatement.setString(1, order.getOrderNumber());
            callableStatement.setDate(2, 
                    java.sql.Date.valueOf(order.getOrderDate()));
            callableStatement.setInt(3, order.getVendorId());

            callableStatement.execute();

        } catch(SQLException ex){
            throw new OrderDataException(ex);
        }
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
        Order order = null;
        try{
            Connection conn = buildConnection();
            CallableStatement callableStatement
                    = conn.prepareCall("CALL sp_get_Order_by_Order_Number(?);");
            callableStatement.setString(1, orderNumber);

            ResultSet resultSet = callableStatement.executeQuery();
            LocalDate orderDate;
            int vendorId;
            if(resultSet.next()){
                orderDate = resultSet.getDate("Order_Date").toLocalDate();
                vendorId = resultSet.getInt("Vendor_Id");
                order = new Order(orderNumber, orderDate, vendorId);
            }


        } catch(SQLException ex){
            throw new OrderDataException(ex);
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
        List<Order> orders = new ArrayList<>();
        try{
            Connection conn = buildConnection();
            CallableStatement callableStatement
                    = conn.prepareCall("CALL sp_get_all_Order();");

            ResultSet resultSet = callableStatement.executeQuery();
            String orderNumber;
            LocalDate orderDate;
            int vendorId;
            while(resultSet.next()){
                orderNumber =resultSet.getString("order_Number");
                orderDate = resultSet.getDate("Order_Date").toLocalDate();
                vendorId = resultSet.getInt("Vendor_Id");
                orders.add(new Order(orderNumber, orderDate, vendorId));
            }


        } catch(SQLException ex){
            throw new OrderDataException(ex);
        }
        return orders;
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
        try{
            Connection conn = buildConnection();
            CallableStatement callableStatement
                    = conn.prepareCall("CALL sp_update_Order(?,?,?,?,?,?,?,?);");
            callableStatement.setString(1, original.getOrderNumber());
            callableStatement.setDate(2, 
                    java.sql.Date.valueOf(original.getOrderDate()));
            callableStatement.setInt(3, original.getVendorId());
            callableStatement.setString(4, updated.getOrderNumber());
            callableStatement.setDate(5, 
                    java.sql.Date.valueOf(updated.getOrderDate()));
            callableStatement.setInt(6, updated.getVendorId());

            callableStatement.execute();

        } catch(SQLException ex){
            throw new OrderDataException(ex);
        }
    }

    /**
     * Permanently deletes the supplied Order record
     *
     * @param order the Order to delete
     * @throws OrderDataException
     */
    @Override
    public void deleteOrder(Order order) throws OrderDataException {
        try{
            Connection conn = buildConnection();
            CallableStatement callableStatement
                    = conn.prepareCall("CALL sp_delete_from_Order(?,?,?,?);");
            callableStatement.setString(1, order.getOrderNumber());
            callableStatement.setDate(2, 
                    java.sql.Date.valueOf(order.getOrderDate()));
            callableStatement.setInt(3, order.getVendorId());
            callableStatement.execute();

        } catch(SQLException ex){
            throw new OrderDataException(ex);
        }
    }

    /**
     * Permanently deletes the Order record with the supplied order number value.
     *
     * @param orderNumber the unique identifier for the Order to be deleted
     * @throws OrderDataException
     */
    @Override
    public void deleteOrder(String orderNumber) throws OrderDataException {
        deleteOrder(getOrderByOrderNumber(orderNumber));
    }

}

