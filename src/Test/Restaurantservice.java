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
import utility.ConsoleText;

public class Restaurantservice {

    Scanner sc = new Scanner(System.in);
    private Restaurant res;
    private Location resLocation;
    private static int personCounter;

    public void welcomeMenu() {
        String textInput;
        do {
            StringBuilder str = new StringBuilder();
            str.append("-----------------");
            str.append(ConsoleText.BLACK + " WELCOME TO");
            str.append(ConsoleText.RED + " SIT EATERY ");
            str.append(ConsoleText.BLACK + "-----------------" + '\n');
            str.append("\t" + "Have you ever registed in this restaurant ?");
            str.append(ConsoleText.BLUE + " yes " + ConsoleText.BLACK + "or" + ConsoleText.RED + " no ");
            str.append('\n' + "type 0 to exit");
            System.out.println(str);
            textInput = sc.nextLine();
            switch (textInput) {
                case "yes":
                    orderFood();
                    break;
                case "no":
                    register();
                    break;
                case "0":
                    break;
            }

        } while (!textInput.equals("0"));

    }

    private void register() {
        String name;
        String phone;
        String address;
        double latitude;
        double longtitude;
         System.out.println(ConsoleText.BLACK + "Enter your name");
            name = sc.nextLine();
            System.out.println("your phone number ");
            phone = sc.nextLine();
            Person p1 = new Person(name, phone);
            System.out.println("Enter your Location" + ", address name" + ", Latitude" + ", Longitude");
            address = sc.nextLine();
            latitude = sc.nextDouble();
            longtitude = sc.nextDouble();
            sc.nextLine();
            Location r_p1 = new Location(address, latitude, longtitude);
            CustomerAccount c_p1 = new CustomerAccount(r_p1, AccountStatus.ACTIVE, p1);
            System.out.println(c_p1.toString());
//         orderFood();
    }

    
    
    
    
    

    private void orderFood() {
        
        System.out.println("Enter your registed name and phone ");
        
        

    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Restaurantservice reService = new Restaurantservice();
        Location resLc = new Location("SIT EATERY ", 13.652714, 100.493614);
        Restaurant rs = new Restaurant("SIT EATERY", resLc);
        ArrayList<Food> foodList = null;
        foodList = rs.getFoods();
        rs.addFoodMenu(foodList);
//
//        System.out.println("---------------------");
//        Person r1 = new Person("Jennie", "0857891025");
//        Location ct_l = new Location("cosmo mansion", 12.651444, 120.498361);
//        CustomerAccount ct1 = new CustomerAccount(ct_l, AccountStatus.ACTIVE, r1);

//        System.out.println(ct1.getMyProfile());
//        rs.addItemIntoBasket(ct1, 219);
//        rs.addItemIntoBasket(ct1, 310);
//        rs.addItemIntoBasket(ct1, 108);
//        rs.addItemIntoBasket(ct1, 110);
//        rs.addItemIntoBasket(ct1, 207);
////        rs.getMyOrderList(ct1);
//        rs.addItemIntoBasket(ct1, 218);
//        rs.addItemIntoBasket(ct1, 219);
//        rs.addItemIntoBasket(ct1, 310);
//        rs.addItemIntoBasket(ct1, 108);
//        rs.addItemIntoBasket(ct1, 110);
//
////        rs.getMyOrderList(ct1);
        reService.welcomeMenu();
//        rs.checkoutItem(ct1);
////        rs.getMyBill(ct1);
//
////        rs.checkoutItem(ct1);
////        System.out.println("---------------");
////        rs.getMyBill(ct1);

    }
}
