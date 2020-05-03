/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.service;

import dataaccess.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import restaurant.location.Location;
import restaurant.menu.Food;
import restaurant.policy.PolicyOrdering;
import restaurant.service.CustomerAccount;
import restaurant.service.CustomerService;
import restaurant.service.Order;
import restaurant.service.RestaurantService;

public class Restaurant implements RestaurantService, CustomerService, PolicyOrdering {

    private String restaurantName;
   public  Food[] foodmenu;
    public static Location resLc;
    private Order order[];
    private CustomerAccount customers[];
//    public Comparator<Location> locationComparator;
    private int foodCounter;
    private int orderCounter = 0;
    private int customerCounter;

    public Restaurant(String restaurantName, Location resLc) {
        this.restaurantName = restaurantName;
        this.resLc = resLc;
        order = new Order[PolicyOrdering.MAX_ORDER_PER_CUSTOMER];
        // customers = new CustomerAccount[maxCustomer];

    }

    public static ArrayList<Food> getFoods() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select * From foodmenu";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ArrayList<Food> foodlist = new ArrayList<>();
        while (rst.next()) {
            Food foodmenu = new Food(rst.getInt("food_id"), rst.getString("food_name"),rst.getInt("food_price"));
            foodlist.add(foodmenu);
        }
        return foodlist;
    }

    public void addFoodMenu(ArrayList<Food> food) {
            foodmenu = new Food[food.size()];
        for (int i = 0; i < food.size(); i++) {
           foodmenu[i] = food.get(i);
        }
        if (foodmenu==null) {
            System.out.println("add foodmenu false");
        }
//         if (foodmenu !=null) {
//             for (int i = 0; i < foodmenu.length; i++) {
//                 System.out.println(foodmenu[i].toString());
//             }
//        }
    }
    
    public void showFoodMenu(int foodType){
        switch (foodType) {
            case 1:
                for (int i = 0; i < 10; i++) {
                    System.out.println(foodmenu[i]);
                }
                break;
                  case 2:
                for (int i = 10; i < 30; i++) {
                    System.out.println(foodmenu[i]);
                }
                break;
                  case 3:
                for (int i = 30; i < foodmenu.length; i++) {
                    System.out.println(foodmenu[i]);
                }
                break;
            default:
                throw new AssertionError();
        }
        }

    
    @Override
    public boolean addItemIntoBasket(CustomerAccount customer, int foodId) {
        int foodIndex = findForFoodId(foodId);
//        System.out.println(foodIndex);
        int orderIndex = findForWhoseOrder(customer);
//        System.out.println(orderIndex);
        if (foodIndex > -1) {
            if (orderIndex > -1) {
                order[orderIndex].addItemIntoBasket(foodmenu[foodIndex]);
//                System.out.println("Add item success");
                return true;
            } else {
                order[orderCounter++] = new Order(customer);
                order[orderCounter - 1].addItemIntoBasket(foodmenu[foodIndex]);
//                System.out.println("Add item success");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delItemFromBasket(CustomerAccount customer, int foodId) {
        int foodIndex = findForFoodId(foodId);
        int orderIndex = findForWhoseOrder(customer);
        if (foodIndex > -1) {
           
            if (orderIndex > -1) {
                System.out.println(order[orderIndex].delItemFromBasket(foodmenu[foodIndex]));
//                order[orderIndex].delItemFromBasket(foodMenu[foodIndex]);
//                System.out.println("Delete success");
                return true;
            }
        }

        return false;
    }

    @Override
    public void checkoutItem(CustomerAccount customer) {
        int orderIndex = findForWhoseOrder(customer);
        if (orderIndex > -1) {
            order[orderIndex].checkoutItem();
            System.out.println("your current fee now  is : "+order[orderIndex].getTotalFee() +" baht");
            System.out.println("----------------------");

        }

    }

    @Override
    public boolean getMyOrderList(CustomerAccount customer) {
        int orderIndex = findForWhoseOrder(customer);
//        System.out.println(orderIndex);
        if (orderIndex > -1) {
            order[orderIndex].getOrderList();
             System.out.println("The number of your food now is: "+order[orderIndex].getFoodCounter());
             System.out.println("Your current fee now  is : "+order[orderIndex].getFoodFee()+" baht");
               
            System.out.println("The delivery fee is not inclued yet ");
            
            return true;
        }
        return false;
    }

    @Override
    public void getMyBill(CustomerAccount customer) {
        int orderIndex = findForWhoseOrder(customer);
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        if (orderIndex > -1) {
            sb2.append(customer.getMyProfile().getName());
            System.out.println(sb2);
            order[orderIndex].getOrderList();
//            sb.append(order[orderIndex].getOrderList());
            sb.append("Yout total fee:");
            sb.append(order[orderIndex].getTotalFee());
            sb.append(",Food fee: ");
            sb.append(order[orderIndex].getFoodFee());
            sb.append(",Delivery  fee: ");
            sb.append(order[orderIndex].getDeliveryFee());
            sb.append("\n");
            sb.append("Thank you, have a nice day");
            System.out.println(sb);
        } else {
            System.out.println("You don't have any order to be found  ");
        }

    }

    private int findForFoodId(int foodId) {
        int foodIdIndex = -1;
        for (int i = 0; i < foodmenu.length; i++) {
            if (foodmenu[i] != null) {
                if (foodmenu[i].getFoodId() == foodId) {
                    return foodIdIndex = i;
                }
            }

        }
        return foodIdIndex;
    }

    private int findForWhoseOrder(CustomerAccount customer) {
        if (customer != null && customer.getMyProfile() != null) {
            for (int i = 0; i < orderCounter; i++) {
                if (order[i] != null && order[i].getCustomer().getMyProfile() != null) {
                    if (customer.getMyProfile().getName().equals(order[i].getCustomer().getMyProfile().getName())) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return  restaurantName ;
    }

}

