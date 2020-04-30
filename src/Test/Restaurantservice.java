/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import restaurant.location.Location;
import restaurant.menu.Food;
import restaurant.person.AccountStatus;
import restaurant.person.Person;
import restaurant.service.CustomerAccount;
import restaurant.service.Restaurant;

public class Restaurantservice {

    public static void main(String[] args) {
        Location resLc = new Location("sit cafe", 13.652714, 100.493614);
        Restaurant rs = new Restaurant("sit kitchen", resLc);
        Location cus_1 = new Location("cosmo", 13.651444, 100.498361);
        Person q1 = new Person("qwan", "0620050195");
        CustomerAccount q = new CustomerAccount(cus_1, AccountStatus.ACTIVE, q1);
        //  System.out.println(q.getMyProfile());
        Food f1 = new Food(100, "chocolate frapped", 40);
        Food f2 = new Food(101, "iced chocolate ", 45);
        Food food[] = {f1, f2};
        rs.addFoodMenu(food);
        rs.addItemIntoBasket(q, 100);
        rs.addItemIntoBasket(q, 101);
       // rs.getMyOrderList(q);
        System.out.println("------------");
        rs.delItemFromBasket(q, 100);
        rs.getMyOrderList(q);
//        rs.checkoutItem(q);
//        rs.getMyBill(q);

    }

}
