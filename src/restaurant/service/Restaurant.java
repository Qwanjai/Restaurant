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
import restaurant.person.Person;
import restaurant.policy.PolicyOrdering;
import restaurant.service.CustomerAccount;
import restaurant.service.CustomerService;
import restaurant.service.Order;
import utility.ConsoleText;

public class Restaurant implements CustomerService, PolicyOrdering {

    public Food[] foodmenu;
    public static Location resLc;
    private String restaurantName;
    private Order order[];
    private CustomerAccount customers[];
    private int foodCounter;
    private int orderCounter;
    private int nOrderForId = 0;
    private int orderId;


    public Restaurant(String restaurantName, Location resLc) {
        this.restaurantName = restaurantName;
        this.resLc = resLc;
        order = new Order[PolicyOrdering.MAX_ORDER_PER_CUSTOMER];
        this.orderId = generateOrderId();
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
            Food foodmenu = new Food(rst.getInt("food_id"), rst.getString("food_name"), rst.getInt("food_price"));
            foodlist.add(foodmenu);
        }
        return foodlist;
    }

    public void addFoodMenu(ArrayList<Food> food) {
        foodmenu = new Food[food.size()];
        for (int i = 0; i < food.size(); i++) {
            foodmenu[i] = food.get(i);
        }
        if (foodmenu == null) {
            System.out.println("add foodmenu false");
        }
//         if (foodmenu !=null) {
//             for (int i = 0; i < foodmenu.length; i++) {
//                 System.out.println(foodmenu[i].toString());
//             }
//        }
    }

    public void showFoodMenu(int foodType) {
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
                return true;
            } else {
                order[orderCounter++] = new Order(customer);
                order[orderCounter - 1].addItemIntoBasket(foodmenu[foodIndex]);
                nOrderForId++;
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
                order[orderIndex].delItemFromBasket(foodmenu[foodIndex]);
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
            getMyBill(customer);
            clearOrder(customer);
        }

    }
//

    private boolean clearOrder(CustomerAccount customer) {
        int orderIndex = findForWhoseOrder(customer);
        if (orderIndex > -1) {
            for (int i = orderIndex; i < orderCounter; i++) {
                try {
                    order[i] = order[i + 1];
                } catch (IndexOutOfBoundsException ex) {
                    order[i] = null;
                }
            }
            orderCounter--;
            return true;
        }

        return false;
    }

    @Override
    public boolean getMyOrderList(CustomerAccount customer) {
        int orderIndex = findForWhoseOrder(customer);
//        System.out.println(orderIndex);
        if (orderIndex > -1) {
            order[orderIndex].getOrderList();
            System.out.println("The number of your food now is: " + order[orderIndex].getFoodCounter());
            System.out.println("Your current fee now  is : " + order[orderIndex].getFoodFee() + " baht");

            System.out.println("The delivery fee is not inclued yet ");

            return true;
        }
        return false;
    }

    private void getMyBill(CustomerAccount customer) {
        int orderIndex = findForWhoseOrder(customer);
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        if (orderIndex > -1) {
            sb2.append("   " + customer.getMyProfile().getName());
            sb2.append("   | Order number : " + generateOrderId());
            System.out.println(sb2);
            order[orderIndex].getOrderList2();
            sb.append("Yout total fee: ");
            sb.append(order[orderIndex].getTotalFee());
            sb.append("   , Food fee: ");
            sb.append(order[orderIndex].getFoodFee());
            sb.append(", Delivery fee: ");
            sb.append(order[orderIndex].getDeliveryFee());
            sb.append("\n");
            sb.append("Thank you, have a nice day");
            System.out.println(sb);
        } else {
            System.out.println(ConsoleText.RED+"You don't have any order to be found  "+ConsoleText.BLACK);
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

    private int generateOrderId() {
        String orderIdS = "200" + String.valueOf(nOrderForId);
        orderId = Integer.parseInt(orderIdS);
        return orderId;

    }

    public int getnOrderForId() {
        return nOrderForId;
    }

    @Override
    public String toString() {
        return restaurantName + "orderId :" + orderId;
    }

}
