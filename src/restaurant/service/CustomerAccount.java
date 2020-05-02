/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.service;

import java.util.Objects;
import restaurant.location.Location;
import restaurant.person.AccountStatus;
import restaurant.person.Person;


public class CustomerAccount {

    private Location myLocation;
    private AccountStatus status;
    private Person person;
    public int  cusId;

    public CustomerAccount(Location myLocation, AccountStatus status, Person person) {
    
        this.myLocation = myLocation;
        this.status = status;
        this.person = person;
        this.cusId=generateCusId();
    }
  
    
    
      private int generateCusId(){
        String id;
        id = "100"+String.valueOf(Person.getCountPerson());
        return Integer.parseInt(id);
       
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
        final CustomerAccount other = (CustomerAccount) obj;
        if (!Objects.equals(this.myLocation, other.myLocation)) {
            return false;
        }
        if (!Objects.equals(this.person, other.person)) {
            return false;
        }
        return true;
    }

    public Person getMyProfile() {
        return person;
    }

    public Location getMyLocation() {
        return myLocation;
    }

    @Override
    public String toString() {

        return "myLocation=" + myLocation + ", status=" + status + ", person=" + person + cusId;
    }

}
