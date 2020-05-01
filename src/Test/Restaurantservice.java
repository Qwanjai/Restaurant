/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import dataaccess.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import restaurant.location.Location;
import restaurant.menu.Food;
import restaurant.person.AccountStatus;
import restaurant.person.Person;
import restaurant.service.CustomerAccount;
import restaurant.service.Restaurant;
import java.util.Scanner;
import utility.ConsoleColors;
public class Restaurantservice {
   Scanner sc =  new Scanner(System.in);
    private  Restaurant res;
    private  Location resLocation;

    public void welcomeMenu(){
       String textInput=null ; 
        while (textInput != "0") {
             StringBuilder st1 = new StringBuilder();
            st1.append(ConsoleColors.BLACK_UNDERLINED+"WELCOME TO "+"SIT EATERY");
            st1.append('\n'+"Have you ever ordered in this restauant"+"?");
            st1.append(ConsoleColors.RED+"  yes / no");
           st1.append('\n'+"type 0 for exiting");
           if (textInput == "no") {
                register();
            }else if (textInput == "yes"){
                orderFood();
            } 
           
            
            System.out.println(st1);
        }

        
        
        
    }
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Restaurantservice reService = new Restaurantservice();
        Location resLc = new Location("SIT EATERY ", 13.652714, 100.493614);
        Restaurant rs = new Restaurant("SIT EATERY", resLc);
        ArrayList<Food> foodList = null;
        foodList = rs.getFoods();
        rs.addFoodMenu(foodList);

        System.out.println("---------------------");
//        Person r1 = new Person("Jennie", "0857891025");
//        Location ct_l = new Location("cosmo mansion", 12.651444, 120.498361);
//        CustomerAccount ct1 = new CustomerAccount(ct_l, AccountStatus.ACTIVE, r1);
//        System.out.println(ct1.getMyProfile());
//        rs.addItemIntoBasket(ct1, 219);
//        rs.addItemIntoBasket(ct1, 310);
//        rs.addItemIntoBasket(ct1, 108);
//        rs.addItemIntoBasket(ct1, 110);
//        rs.addItemIntoBasket(ct1, 207);
//        rs.getMyOrderList(ct1);
//        rs.addItemIntoBasket(ct1, 218);
//        rs.addItemIntoBasket(ct1, 219);
//        rs.addItemIntoBasket(ct1, 310);
//        rs.addItemIntoBasket(ct1, 108);
//        rs.addItemIntoBasket(ct1, 110);

//        rs.getMyOrderList(ct1);
        reService.welcomeMenu();
//        rs.checkoutItem(ct1);
//        rs.getMyBill(ct1);

//        rs.checkoutItem(ct1);
//        System.out.println("---------------");
//        rs.getMyBill(ct1);
        
    }

    private void register() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void orderFood() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
