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
import java.sql.SQLException;
import restaurant.service.CustomerAccount;


public class CustomerDBImp implements CustomerDB {

    @Override
    public void insert(CustomerAccount customer) {
      String sqlCustomer = "INSERT INTO customers (cus_id,cus_name,cus_phone) VALUES(?,?,?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sqlCustomer)) {
            pstmt.setInt(1,customer.getCusId() );
            pstmt.setString(2, customer.getMyProfile().getName());
            pstmt.setString(3, customer.getMyProfile().getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//    @Override
//    public int update(CustomerAccount customer) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    
   } 
    
    
    

