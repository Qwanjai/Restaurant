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

/**
 *
 * @author rewbuglag
 */
public class CustomerAccount {

    private Location myLocation;
    private AccountStatus status;
    private Person person;

    public CustomerAccount(Location myLocation, AccountStatus status, Person person) {
        this.myLocation = myLocation;
        this.status = status;
        this.person = person;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        if (this.status != other.status) {
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

}