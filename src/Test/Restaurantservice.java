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
import restaurant.policy.PolicyOrdering;
import restaurant.service.Order;
import utility.ConsoleText;

public class Restaurantservice {
    
    Scanner sc = new Scanner(System.in);
    private Location resLocation;
    private static int personCounter;
    public Restaurantservice[] rsService;
    public int dataCounter;
    public Restaurant rs;
    private CustomerAccount[] c;
    private Order o_c;
    private int cusIndex;
    
    public void welcomeMenu() {
        String textInput;
        do {
            StringBuilder str = new StringBuilder();
            str.append("-------------------------------------------------"+'\n');
            str.append("\t"+ConsoleText.BLACK + "      WELCOME TO");
            str.append(ConsoleText.RED + " SIT EATERY "+'\n');
            str.append(ConsoleText.BLACK + "-------------------------------------------------" + '\n');
            str.append( "Have you ever registed in this restaurant ?");
//            str.append(ConsoleText.BLUE + " yes " + ConsoleText.BLACK + "or" + ConsoleText.RED + " no ");
            str.append(ConsoleText.BLACK + "     type 0 to exit");
            str.append('\n');
            str.append('\n'  + ConsoleText.BLUE + " yes " + ConsoleText.BLACK + "if you registed");
            str.append("     |     " + ConsoleText.RED + " no " + ConsoleText.BLACK + "if you didn't registed");
            System.out.println(str);
            textInput = sc.nextLine();
            switch (textInput) {
                case "yes":
                    signIn();
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
        System.out.print(ConsoleText.BLACK + "Enter your name : ");
        name = sc.nextLine();
        System.out.print("Your phone number : ");
        phone = sc.nextLine();
        Person p1 = new Person(name, phone);
        System.out.println("Enter your Location | " + " Address name" + ", Latitude" + ", Longitude");
        System.out.print("Your Address name : ");
        address = sc.nextLine();
        System.out.print("Latitude : ");
        latitude = sc.nextDouble();
        System.out.print("Longitude : ");
        longtitude = sc.nextDouble();
        sc.nextLine();
        Location r_p1 = new Location(address, latitude, longtitude);
        dataCounter++;
        for (int i = 1; i < c.length; i++) {
            c[i] = new CustomerAccount(r_p1, p1);
        }
        System.out.println(c[dataCounter].toString() + "  Customer id is : " + ConsoleText.BLUE + c[dataCounter].getCusId());
//        System.out.print(ConsoleText.BLUE + c[dataCounter].getCusId());

//         orderFood();
    }
    
    private boolean signIn() {
        int id;
        System.out.println("|----------------- Sing in menu -----------------|");
        System.out.print("Enter your customer id :");
        id = sc.nextInt();
        sc.nextLine();
        cusIndex = findForAccount(id);
        if (cusIndex == -1) {
            return false;
        }
//        System.out.println(c[cusIndex]);
        orderFood();
        return true;
    }
    
    private void orderFood() {
        int menuId;
        do {
             System.out.println("|----------------- Order  menu -----------------|");
        System.out.println("(1) Show food menu ");
        System.out.println("(2) Add food to basket ");
        System.out.println("(3) Delete food from basket ");
        System.out.println("(4) See your orderlist ");
        System.out.println("(5) Checkout ");
        System.out.println("(6) Get your reciept");
         System.out.println("(7) Exit out ");
        menuId = sc.nextInt();
        sc.nextLine();
        switch (menuId) {
            case 1:
                showFoodMenu();
                break;
            case 2:
                addFoodIntoBasket();
                break;
            case 3:
                deleteFoodFromBasket();
                break;
            case 4:
                showOrderlist();
                break;
            case 5:
                checkout();
                break;
            case 6:
                getBill();
                break;
            case 7:
                break;
            default:
                System.out.println("Please, try again");
        }
        } while (menuId!=7);
        
       
        
    }
    
    private void showFoodMenu() {
        int menu;
        System.out.println(" (1) Drinks " + " (2) Main dish " + " (3) Dessert ");
        System.out.println("Enter (1-3) to see the menu : ");
        menu = sc.nextInt();
        rs.showFoodMenu(menu);
        System.out.println("----------------------------------------------------");
    }
    
    private int findForAccount(int id) {
        int accountindex = -1;
        for (int i = 1; i < c.length; i++) {
            if (c[i] != null && c[i].getCusId() == id) {
                return i;
            }
        }
        System.out.println(ConsoleText.RED+"Account not found");
        return accountindex;
    }
    
    public Restaurantservice() {
        this.c = new CustomerAccount[PolicyOrdering.MAX_CUS];
    }
    
    private boolean addFoodIntoBasket() {
        int foodId;
        boolean loop = true;         
        while (loop) {
            System.out.println("Type the food id you want to add , Type 1 to back to the Order menu"); 
            foodId = sc.nextInt();
            if (foodId == 1) {
                return false;
            }
            if (!rs.addItemIntoBasket(c[cusIndex], foodId)) {
                System.out.println(ConsoleText.RED+"Food not found");
                continue;
            }
            System.out.println(ConsoleText.GREEN+"Add item success");
        }
        return false;
    }
    
    private boolean deleteFoodFromBasket() {
         int foodId;
        boolean loop = true;         
        while (loop) {
            System.out.println("Type the food id you want to delete  , Type 1 to back to the Order menu"); 
            foodId = sc.nextInt();
            if (foodId == 1) {
                return false;
            }
            if (!rs.delItemFromBasket(c[cusIndex], foodId)) {
                System.out.println(ConsoleText.RED+"Food not found");
                continue;
            }
            System.out.println(ConsoleText.GREEN+"Delete item success");
        }
        return false;
        
    }
    
    private void showOrderlist() {
        rs.getMyOrderList(c[cusIndex]);
    }
    
    private void checkout() {
        rs.checkoutItem(c[cusIndex]);
        
    }
    
    private void getBill() {
        rs.getMyBill(c[cusIndex]);
        
    }

    private void setValueOfRestaurant() throws ClassNotFoundException, SQLException {
        resLocation = new Location("SIT EATERY ", 13.652714, 100.493614);
        rs = new Restaurant("SIT EATERY", resLocation);        
        ArrayList<Food> foodList = null;
        foodList = rs.getFoods();
        rs.addFoodMenu(foodList);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Restaurantservice resNew = new Restaurantservice();
        resNew.setValueOfRestaurant();
        resNew.welcomeMenu();
        
    }
}
