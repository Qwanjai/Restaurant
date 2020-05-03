/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.service;

import restaurant.menu.Food;


public interface CustomerService {

    public boolean addItemIntoBasket(CustomerAccount customer, int foodId);

    public boolean delItemFromBasket(CustomerAccount customer, int foodId);

    public boolean getMyOrderList(CustomerAccount customer);
 

    public void checkoutItem(CustomerAccount customer);

}
