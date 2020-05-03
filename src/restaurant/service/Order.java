/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import restaurant.location.Location;
import restaurant.menu.Food;
import restaurant.policy.PolicyOrdering;
import restaurant.service.Restaurant;

public class Order {

//    private LocalDateTime orderDate;
    private Food orderlist[];
    private CustomerAccount customer;
    private OrderStatus status;
    private int totalFee;
    private int foodCounter;
    private Location resLocate;
    private int foodFee;
    private int deliveryFee;

    public Order(CustomerAccount customer) {
        this.resLocate = Restaurant.resLc;
        this.customer = customer;
        this.orderlist = new Food[PolicyOrdering.MAX_ORDER_PER_CUSTOMER];

    }

    public boolean addItemIntoBasket(Food food) {
        if (foodCounter == PolicyOrdering.MAX_ORDER_PER_CUSTOMER) {
            
            return false;
        }

        orderlist[foodCounter] = food;
        foodCounter++;
        status = OrderStatus.ORDERING;
        return true;
    }

    public boolean delItemFromBasket(Food food) {
        int orderIndex = findForOrderList(food);
        if (orderIndex > -1) {
            for (int i = orderIndex; i < foodCounter; i++) {
                try {
                    orderlist[i] = orderlist[i + 1];
                } catch (IndexOutOfBoundsException ex) {
                    orderlist[i] = null;
                }
            }
            foodCounter--;
            status = OrderStatus.ORDERING;
            return true;
        }

        return false;
    }

    public int findForOrderList(Food food) {
        for (int i = 0; i < orderlist.length; i++) {
            if (food.equals(orderlist[i])) {
                return i;
            }
        }
        return -1;
    }

    public void checkoutItem() {
        for (int i = 0; i < foodCounter; i++) {
            foodFee += orderlist[i].getPrice();
        }
        deliveryFee = distanceTo(resLocate, customer) * PolicyOrdering.FEE_PER_KILOMETER;
        totalFee = foodFee + deliveryFee;
        status = OrderStatus.FINISHED;

    }

    public CustomerAccount getCustomer() {
        return customer;
    }

    public void getOrderList() {
        for (int i = 0; i < foodCounter; i++) {
            System.out.println(orderlist[i]);
            foodFee += orderlist[i].getPrice();
        }
        status=OrderStatus.ORDERING;
        
    }

    public OrderStatus getOrderStatus() {
        return status;
    }

    public Order getBill() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Arrays.deepEquals(this.orderlist, other.orderlist)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }

        return true;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public int getFoodFee() {
        return foodFee;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    private int distanceTo(Location resLocate, CustomerAccount customer) {
        double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
        double lat1 = Math.toRadians(resLocate.getLatitude());
        double lon1 = Math.toRadians(resLocate.getLongitude());
        double lat2 = Math.toRadians(customer.getMyLocation().getLatitude());
        double lon2 = Math.toRadians(customer.getMyLocation().getLongitude());

        // great circle distance in radians, using law of cosines formula
        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        // each degree on a great circle of Earth is 60 nautical miles
        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        int distance = (int) Math.ceil(statuteMiles);
        return distance;
    }

    public int getFoodCounter() {
        return foodCounter;
    }

}
