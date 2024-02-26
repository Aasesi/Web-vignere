/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.wilczynskimikolaj.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class manages handling database.
 *
 * @author Mikołaj
 * @version 1.0
 */
public class DatabaseHandler {

    private Connection con;

    /**
     * Connects to the database.
     */
    public void connect() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found");
        }
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "app", "app");
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
        }

    }

    /**
     * Creates table if it does not exists.
     */
    public void createTableIfNotExists() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found");
        }
        try {
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "OPERATIONS", new String[]{"TABLE"});

            if (!tables.next()) {
                Statement statement = con.createStatement();
                statement.executeUpdate("CREATE TABLE Operations (typeOfChange VARCHAR(50),"
                        + " changedText VARCHAR(50), originalText VARCHAR(50),"
                        + " keyy VARCHAR(50))");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Inserts new operation.
     *
     * @param operation row of the table with the data of operation as a class
     */
    public void insertOperation(Operation operation) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found");
        }
        try {
            Statement statement = con.createStatement();
            String sql = "INSERT INTO Operations (typeOfChange, changedText, originalText, keyy) "
                    + "VALUES ('" + operation.getTypeOfChange() + "', '" + operation.getChangedText()
                    + "', '" + operation.getOriginalText() + "', '" + operation.getKey() + "')";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Retrives all operations from database.
     *
     * @return List of all operations.
     */
    public List<Operation> getAllOperations() {
        List<Operation> operations = new ArrayList<>();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Operations");
            while (resultSet.next()) {
                Operation operation = new Operation();
                operation.setTypeOfChange(resultSet.getString("typeOfChange"));
                operation.setChangedText(resultSet.getString("changedText"));
                operation.setOriginalText(resultSet.getString("originalText"));
                operation.setKey(resultSet.getString("keyy"));
                operations.add(operation);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return operations;
    }

    /**
     * Function disconnects con.
     */
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Disconnection error: " + ex.getMessage());
            }
        }
    }
}
