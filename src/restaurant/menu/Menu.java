/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.menu;

import java.util.Objects;


public class Menu {
    private int foodId;
    private String foodName;
    private int price;

    public Menu(int foodId, String foodName, int price) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
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
        final Menu other = (Menu) obj;
        if (this.foodId != other.foodId) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        if (!Objects.equals(this.foodName, other.foodName)) {
            return false;
        }
        return true;
    }
    
}
