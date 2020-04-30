/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.menu;

import java.util.Objects;


public class Food {
    private int foodId;
    private String foodName;
    private int price;

    public Food(int foodId, String foodName, int price) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getPrice() {
        return price;
    }

    public String getFoodName() {
        return foodName;
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
        final Food other = (Food) obj;
        if (this.foodId != other.foodId) {
            return false;
        }
        if (!Objects.equals(this.foodName, other.foodName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Food{" + "foodId=" + foodId + ", foodName=" + foodName + ", price=" + price + '}';
    }
    
}
