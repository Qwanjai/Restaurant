/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import dataaccess.DBConnection;
import java.io.*;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DatabaseInitialization {
     public static void main(String[] args) {
        createTables();
        initializeDb(true);
    }
    
    public static void reNew() {
        createTables();
        initializeDb(false);
    }
    
    private static void createTables() {
        try (Connection conn = DBConnection.getConnection();
                Statement stm = conn.createStatement()) {
//           try {stm.executeUpdate("DROP TABLE foodmenu");} catch (SQLException ex) {}       
           try {stm.executeUpdate("DROP TABLE customers");} catch (SQLException ex) {}   
           try {stm.executeUpdate("DROP TABLE orders");} catch (SQLException ex) {}   
            
            try {stm.executeUpdate("CREATE TABLE foodmenu (food_id INT NOT NULL, food_name VARCHAR(200),food_price INT NOT NULL,PRIMARY KEY (food_id))");} catch (SQLException ex) {} 
            try {stm.executeUpdate("CREATE TABLE customers (cus_id INT NOT NULL, cus_name VARCHAR(200),cus_phone VARCHAR(100) ,PRIMARY KEY (cus_id))");} catch (SQLException ex) {} 
            try {stm.executeUpdate("CREATE TABLE orders (ordernumber INT NOT NULL, food_id INT NOT NULL ,cus_id INT NOT NULL ,PRIMARY KEY (ordernumber,food_id))");} catch (SQLException ex) {} 
        }catch (Exception ex) {
            System.out.println(ex.getMessage()+" Go to Service which located to your left top side and Connect");
        }
    }

    private static void initializeDb(boolean show) {
       String sqlFoodmenu="INSERT INTO foodmenu VALUES(?,?,?)";
        try(Connection conn = DBConnection.getConnection();
                PreparedStatement stm = conn.prepareStatement(sqlFoodmenu)) {
            Scanner sc;
///          read foodmenu from csv file
            try {
                if(show)System.out.println("--- Import Foodmenu ---");
                sc=new Scanner(new File("file/foodmenu.csv"));
                String line =sc.nextLine();
                try{
                    while((line=sc.nextLine())!=null){
                        String[] temp=line.split(",");
                        stm.setInt(1,Integer.parseInt(temp[0]));
                        stm.setString(2,temp[1]);
                        stm.setInt(3, Integer.parseInt(temp[2]));
                        stm.executeUpdate();
                        if(show)System.out.println("Insert: "+line);
                    }
                }catch(NoSuchElementException ex){}
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
