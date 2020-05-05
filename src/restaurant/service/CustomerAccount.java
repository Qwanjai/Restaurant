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
import utility.ConsoleText;


public class CustomerAccount {

    private Location myLocation;
    private AccountStatus status;
    private Person person;
    public int  cusId;

    public CustomerAccount(Location myLocation,  Person person) {
        this.myLocation = myLocation;
        this.person = person;
        this.cusId=generateCusId();
    }

    public CustomerAccount(int cusId ,Person person,Location myLocation ) {
        this.myLocation = myLocation;
        this.person = person;
        this.cusId = cusId;
    }
    
      private int generateCusId(){
        String idString;
        idString = "100"+String.valueOf(Person.getCountPerson());
        int idInt = Integer.parseInt(idString);
        return  idInt;
       
    } public Person getMyProfile() {
        return person;
    }

    public Location getMyLocation() {
        return myLocation;
    }

    public int getCusId() {
        return cusId;
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
        if (!Objects.equals(this.cusId, other.cusId)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {

        return  "  "+person+ConsoleText.BLUE+"     customer id is : "+cusId+ConsoleText.BLACK+myLocation +"\n";
    }

}
