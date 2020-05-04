/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.ArrayList;
import restaurant.service.Restaurant;

/**
 *
 * @author rewbuglag
 */
public interface OderDBImp {

    public void insert(Restaurant res);

    Restaurant findByOrderId(int id);

    ArrayList<Restaurant> findByName(String name);

    ArrayList<Restaurant> getAll();
}
