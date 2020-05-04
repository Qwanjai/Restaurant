/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.location;


public class Location {
    private String address;
    private double latitude  ;
    private double longitude ;

    public Location(String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
      public void  clearCoordinates(){
          this.address = null;
        this.latitude = 0;
        this.longitude = 0;
      }     

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Location : " + "address = " + address + " , latitude = " + latitude + " , longitude = " + longitude ;
    }
     
}
