package edu.kcc.java;

import edu.kcc.java.order.data.OrderDAO;

/**
 * Provides an easy way to make sure all the handler screens require a DAO
 * object.
 *
 * @author Andrew Hanson
 */
public interface OrderDataHandler {

    /**
     * This is the starting point for all of the order task handling operations.
     * 
     * @param dao
     */
    void handleTask(OrderDAO dao);
}
