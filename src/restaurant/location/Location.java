/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.location;


public class Location {
    private String address;
    private  float latitude  ;
    private float longitude ;

    public Location(String address, float latitude, float longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
      public void  clearCoordinates(){
          this.address = null;
        this.latitude = 0;
        this.longitude = 0;
      }     
      
}
