/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.ArrayList;
import restaurant.service.CustomerAccount;
import restaurant.service.Restaurant;

public interface CustomerDB {

    public void insert(CustomerAccount customer);
 
    public CustomerAccount findById(int id);

    public ArrayList<CustomerAccount> findByName(String name);

    public ArrayList<CustomerAccount> getAll();

}
