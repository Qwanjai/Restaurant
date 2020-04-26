/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.service;

/**
 *
 * @author rewbuglag
 */
public interface RestaurantService {
    public void sendToDelivery();
    public int findForWhoseOrder(CustomerAccount customer);
    
    
}
