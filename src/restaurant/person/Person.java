/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.person;

import java.util.Objects;


public class Person {
    private String name;
    private String phone;
    private  static int countPerson;
    private static int  id;
    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
         countPerson++;
        
    }

    public static int getCountPerson() {
        return countPerson;
    }


    
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        final Person other = (Person) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", phone=" + phone + '}';
    }
    
    
    
    
    
}
