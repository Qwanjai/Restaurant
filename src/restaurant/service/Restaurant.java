/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.service;

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
    private Food foodMenu[];
    private Location resLc;
    private Order order[];
    private CustomerAccount customers[];
//    public Comparator<Location> locationComparator;
    private int foodCounter;
    private int orderCounter = 0;
    private int customerCounter;

    public Restaurant(String restaurantName, Location resLc, int maxCustomer) {
        this.restaurantName = restaurantName;
        this.resLc = resLc;
        foodMenu = new Food[NUMBER_OF_FOODMENU];
        customers = new CustomerAccount[maxCustomer];

    }

//    @Override
//    public void sendOrderToCustomer(Order order) {
//            for (int i = 0; i < this.order.length; i++) {
//            this.order[i]
//        }
//        
//
//    }

    @Override
    public boolean addItemIntoBasket(CustomerAccount customer, int foodId) {
        int foodIndex = findForFoodId(foodId);
        int orderIndex = findForWhoseOrder(customer);
        if (foodIndex > -1) {
            if (orderIndex > -1) {
                order[orderIndex].addItemIntoBasket(foodMenu[foodIndex]);
                System.out.println("Add item success");
                return true;
            } else {
                order[orderCounter++] = new Order();
                order[orderCounter - 1].addItemIntoBasket(foodMenu[foodIndex]);
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
                order[orderIndex].delItemFromBasket(foodMenu[foodIndex]);
                System.out.println("Delete success");
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
            System.out.println(order[orderIndex].getTotalFee());
            
        }

    }

    @Override
    public Food[] getMyOrderList(CustomerAccount customer) {
        int orderIndex = findForWhoseOrder(customer);
        if (orderIndex > -1) {
            return order[orderIndex].getOrderList();
        }
        return null;
    }

    @Override
    public void   getMyBill(CustomerAccount customer) {
        int orderIndex = findForWhoseOrder(customer); 
        StringBuilder sb = new StringBuilder();
        if (orderIndex > -1) { 
            sb.append(customer.getMyProfile().getName());
            sb.append("\n");    
            sb.append(order[orderIndex].getOrderList());
            sb.append("\n");
            sb.append("Yout total fee :");
            sb.append(order[orderIndex].getTotalFee());
            sb.append(" ,Food fee : ");
            sb.append(order[orderIndex].getFoodFee());
             sb.append(" ,Delivery  fee : ");
             sb.append(order[orderIndex].getDeliveryFee());
            sb.append("\n");
            sb.append("Thank you, have a nice day");
            System.out.println(sb);
        }else{
            System.out.println("You don't have any order to be found  ");
        }

    }

    private int findForFoodId(int foodId) {
        int foodIdIndex = -1;
        for (int i = 0; i < foodMenu.length; i++) {
            if (foodMenu[i].getFoodId() == foodId) {
                return foodIdIndex = i;

            }
        }
        return foodIdIndex;
    }

    private int findForWhoseOrder(CustomerAccount customer) {
        for (int i = 0; i < order.length; i++) {
            if (customer.getMyProfile().getName().equals(order[i].getCustomer().getMyProfile().getName())) {
                return i;
            }
        }
        return -1;
    }

}
