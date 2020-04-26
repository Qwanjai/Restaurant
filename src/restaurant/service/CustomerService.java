/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.service;

import restaurant.menu.Menu;


public interface CustomerService {

    public boolean addItemIntoBasket(CustomerAccount customer, int foodId);

    public boolean delItemIntoBasket(CustomerAccount customer, int foodId);

    public Menu[] getMyOrderList(CustomerAccount customer);

    public Order getMyBill(CustomerAccount customer);

    public void checkoutItem(CustomerAccount customer);

}
