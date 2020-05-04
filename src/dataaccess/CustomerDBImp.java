/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import restaurant.service.Restaurant;
import dataaccess.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import restaurant.location.Location;
import restaurant.person.Person;
import restaurant.service.CustomerAccount;

public class CustomerDBImp implements CustomerDB {

    static CustomerAccount custA[];

    @Override
    public void insert(CustomerAccount customer) {
        String sqlCustomer = "INSERT INTO customers (cus_id,cus_name,cus_phone,cus_address,latitude,longitude) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sqlCustomer)) {
            pstmt.setInt(1, customer.getCusId());
            pstmt.setString(2, customer.getMyProfile().getName());
            pstmt.setString(3, customer.getMyProfile().getPhone());
            pstmt.setString(4, customer.getMyLocation().getAddress());
            pstmt.setDouble(5, customer.getMyLocation().getLatitude());
            pstmt.setDouble(6, customer.getMyLocation().getLongitude());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public CustomerAccount findById(int id) {
        CustomerAccount cust = null;
        Location lc = null;
        Person p = null;
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
            ResultSet rst = stm.executeQuery("SELECT * FROM customers WHERE cus_id=" + id);
            if (rst.next()) {
               return   new CustomerAccount(rst.getInt("cus_id"), new Person(rst.getString("cus_name"), rst.getString("cus_phone")), new Location(rst.getString("cus_address"), rst.getDouble("latitude"), rst.getDouble("longitude")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 return cust ;
    }

    public ArrayList<CustomerAccount> getCustomers() throws ClassNotFoundException, SQLException { //move to Restaurant to store customer data 
        Connection conn = DBConnection.getConnection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select * From customers";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ArrayList<CustomerAccount> cuslist = new ArrayList<>();
        while (rst.next()) {
//            CustomerAccount cust = new CustomerAccount(rst.getInt("cus_id"),rst.getObject("Person"));
            CustomerAccount cust = new CustomerAccount(rst.getInt("cus_id"), new Person(rst.getString("cus_name"), rst.getString("cus_phone")), new Location(rst.getString("cus_address"), rst.getDouble("latitude"), rst.getDouble("longitude")));
            cuslist.add(cust);
        }
        return cuslist;
    }

    public void addCustomerlistToArray(ArrayList<CustomerAccount> cust) { //move to Restaurant to store customer data 
        custA = new CustomerAccount[cust.size()];
        for (int i = 0; i < cust.size(); i++) {
            custA[i] = cust.get(i);
        }
        if (custA == null) {
            System.out.println("add customerlist to array false");
        }
//        if (custA != null) {
//            for (int i = 0; i < custA.length; i++) {
//                System.out.println(custA[i].toString());
//            }
//        }
    }

    @Override
    public ArrayList<CustomerAccount> findByName(String name) {
        ArrayList<CustomerAccount> custList = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE cus_name like ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, "%" + name + "%");
            ResultSet rst = stm.executeQuery();
            while (rst.next()) {
                CustomerAccount cust = new CustomerAccount(rst.getInt("cus_id"), new Person(rst.getString("cus_name"), rst.getString("cus_phone")), new Location(rst.getString("cus_address"), rst.getDouble("latitude"), rst.getDouble("longitude")));
                custList.add(cust);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return custList;

    }

    @Override
    public ArrayList<CustomerAccount> getAll() {
        ArrayList<CustomerAccount> custs = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
            ResultSet rst = stm.executeQuery("SELECT * FROM customers");
            while (rst.next()) {
                custs.add(new CustomerAccount(rst.getInt("cus_id"), new Person(rst.getString("cus_name"), rst.getString("cus_phone")), new Location(rst.getString("cus_address"), rst.getDouble("latitude"), rst.getDouble("longitude"))));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return custs;
    }

//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        CustomerDBImp cusImp = new CustomerDBImp();
//        System.out.println(cusImp.getAll());
////        System.out.println(cusImp.findById(1001));
////        System.out.println(cusImp.findByName("qwanjai"));
//
//    }

    @Override
    public int update(CustomerAccount customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
    
    
    
    }
}
