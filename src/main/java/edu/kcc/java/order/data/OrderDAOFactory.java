package edu.kcc.java.order.data;

import edu.kcc.java.order.data.csv.OrderDAOCSV;
import edu.kcc.java.order.data.mysql.OrderDAOMySQL;

/**
 * A Factory class to get the correct type of OrderDAO implementation based on
 * configurations.
 *
 * @author Andrew Hanson
 */
public class OrderDAOFactory {

    /**
     * This constant is being used in place of a proper configuration file.
     */
    private static final String DAO_SOURCE = "CSV";


    /**
     * The only instance allowed by the Singleton pattern.
     */
    private static OrderDAOFactory instance;

    /**
     * The private constructor required by the Singleton pattern.
     */
    private OrderDAOFactory(){}

    /**
     * The method by which outside objects may get a reference to the only
     * instance of the OrderDAOFactory.
     *
     * @return
     */
    public static OrderDAOFactory getInstance(){
        if(null == instance){
            instance = new OrderDAOFactory();
        }
        return instance;
    }

    /**
     * Uses the configuration information to return the correct implementation
     * of the OrderDAO interface.
     *
     * @return an implementation of the OrderDAO interface
     */
    public OrderDAO getOrderDAO(){
        OrderDAO dao = null;
        switch(DAO_SOURCE){
            case "CSV":
                dao = new OrderDAOCSV();
                break;
            case "XML":
                break;
            case "MYSQL":
                dao = new OrderDAOMySQL();
                break;
            case "SQL_SERVER":
                break;
            default:
        }
        return dao;
    }


}
