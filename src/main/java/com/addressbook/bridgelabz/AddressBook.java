package com.addressbook.bridgelabz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* @Description - To connect the jdbc server using mysql and retrieve the data from
* the address book table */

public class AddressBook {
    private static AddressBook addressBook;
    private static PreparedStatement addressBookDataStatement;
    private static PreparedStatement prepareStatement;

    public static AddressBook getInstance() {
        if (addressBook == null)
            addressBook = new AddressBook();
        return addressBook;
    }

    private Connection getConnection() throws  SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/address_book?useSSL=false";
        String userName = "root";
        String password = "root";
        Connection connection;
        System.out.println("Connecting to database:" + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection is successful!" + connection);
        return connection;
    }

    public List<Contacts> getAddressBookDataUsingDB() {
        String sql = "SELECT * FROM address_book";
        List<Contacts> contactsList = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactsList = this.getEmployeePayrollData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactsList;
    }
    public int updateContactDetails(String name, String address) {
        String sql = String.format("update Address_Book set address = '%s' where firstName = '%s';", address, name);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /* @Description- to used the sql query for the first name  */
    public List<Contacts> getEmployeePayrollData(String name) {
        List<Contacts> contacts = null;
        String sql = "SELECT * FROM address_book WHERE firstName = ?";
        if (this.addressBookDataStatement == null)
            addressBookDataStatement = this.prepareStatementForAddressBook(sql);
        try {
            addressBookDataStatement.setString(1, name);
            ResultSet resultSet = addressBookDataStatement.executeQuery();
            contacts = this.getEmployeePayrollData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    private PreparedStatement prepareStatementForAddressBook(String sql) {
        try {
            Connection connection = this.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            return prepareStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* @Description- to update number of Contacts in the Database */
    private List<Contacts> getEmployeePayrollData(ResultSet resultSet) {
        List<Contacts> contacts = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int zip = resultSet.getInt("zip");
                long mobileNumber = resultSet.getLong("mobileNumber");
                String emailId = resultSet.getString("emailId");
                contacts.add(new Contacts(id,firstName, lastName, address, city, state, zip, mobileNumber, emailId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }
/* @Description- to Retrieve number of Contacts in the Database by City */

    public List<Contacts> getCount(String city) {
        List<Contacts> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM address_book where city = '%s';", city);
        try (Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contacts = this.getEmployeePayrollData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }
    /* @Description- to Retrieve number of Contacts in the Database by state */

    public List<Contacts> getCountByState(String state) {
        List<Contacts> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM Address_Book where state = '%s';", state);
        try (Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contacts = this.getEmployeePayrollData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }
    /* @Description- to add new contact to address book database using sql statement */

    public Contacts addContact(String firstName, String lastName, String address, String city, String state, int zip, long mobileNumber, String emailId) {
        int contactId = -1;
        Contacts contacts = null;
        String sql = String.format("INSERT INTO address_book (firstName, lastName, address, city, state, zip, mobileNumber, emailId)" +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", firstName, lastName, address, city, state, zip, Long.valueOf(mobileNumber), emailId);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
            if (rowAffected == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) contactId = resultSet.getInt(1);
            }
            contacts = new Contacts(contactId, firstName, lastName, address, city, state, zip, Long.valueOf(mobileNumber), emailId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }
    /* @Description- to add multiple  contact to address book database using sql statement */

    public Contacts addContact(String firstName, String lastName, String address, String city, String state, int zip, Long mobileNumber, String emailId) {
        int contactId = -1;
        Contacts contacts = null;
        String sql = String.format("INSERT INTO address_book (firstName, lastName, address, city, state, zip, mobileNumber, email)" +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", firstName, lastName, address, city, state, zip, Long.valueOf(mobileNumber), emailId);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
            if (rowAffected == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) contactId = resultSet.getInt(1);
            }
            contacts = new Contacts(contactId, firstName, lastName, address, city, state, zip, Long.valueOf(mobileNumber), emailId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}


