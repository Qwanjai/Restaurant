/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.sql.SQLException;
import java.util.ArrayList;
import restaurant.location.Location;
import restaurant.menu.Food;
import restaurant.person.Person;
import restaurant.service.CustomerAccount;
import restaurant.service.Restaurant;
import java.util.Scanner;
import restaurant.policy.PolicyOrdering;
import utility.ConsoleText;
import dataaccess.CustomerDBImp;
import dataaccess.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestRestaurant {

    private CustomerDBImp cusImp;
    Scanner sc = new Scanner(System.in);
    private Location resLocation;
    private Restaurant rs;
    public CustomerAccount[] c;
    private int cusIndex;

    public void welcomeMenu() throws ClassNotFoundException, SQLException {
        String textInput;
        do {
            StringBuilder str = new StringBuilder();
            str.append("\n");
            str.append("-------------------------------------------------" + '\n');
            str.append("\t" + ConsoleText.BLACK + "      WELCOME TO");
            str.append(ConsoleText.RED + " SIT EATERY " + '\n');
            str.append(ConsoleText.BLACK + "-------------------------------------------------" + '\n');
            str.append("Are you a owner or a customer ?" + '\n');
            str.append(ConsoleText.BLUE + " (1) : Owner " + '\n');
            str.append(ConsoleText.PURPLE + " (2) : Customer " + '\n');
            str.append(ConsoleText.RED + " (3) : Exit out " + '\n' + ConsoleText.BLACK);
            str.append("Type 1 - 3 here :");
            System.out.print(str);
            textInput = sc.nextLine();
            switch (textInput) {
                case "1":
                    ownerMenu();
                    break;
                case "2":
                    customerMenu();
                    break;
                case "3":
                    break;
            }

        } while (!textInput.equals("3"));

    }

    private void customerMenu() throws ClassNotFoundException, SQLException {
        String textInput;
        do {
            StringBuilder str = new StringBuilder();
            str.append("-------------------------------------------------");
            str.append('\n' + ConsoleText.RED_UNDERLINED + "\t" + "      Customer  menu".toUpperCase() + ConsoleText.BLACK);
            str.append('\n'+"have you ever registed in this restaurant ?".toUpperCase());
            str.append(ConsoleText.BLUE + " yes " + ConsoleText.BLACK + "or" + ConsoleText.RED + " no " + ConsoleText.BLACK);
            str.append('\n');
            str.append("》" + ConsoleText.GREEN + " yes " + ConsoleText.BLACK + "if you registed        (sign in with id)" + ConsoleText.BLACK);
            str.append("\n" + "》" + ConsoleText.BLUE + " no " + ConsoleText.BLACK + "if you didn't registed   (sign up now)");
            str.append("\n" + "》" + ConsoleText.RED + " type 0 to exit" + ConsoleText.BLACK+'\n');
            str.append("Type here  :");
            System.out.print(str);
            textInput = sc.nextLine();
            switch (textInput) {
                case "yes":
                    signIn();
                    break;
                case "no":
                    register();
                    break;
                case "0":
                    break;
            }

        } while (!textInput.equals("0"));

    }

    private void register() throws ClassNotFoundException, SQLException {
        String name;
        String phone;
        String address;
        double latitude;
        double longtitude;
        System.out.print(ConsoleText.BLACK + "Enter your name : ");
        name = sc.nextLine();
        System.out.print("Your phone number : ");
        phone = sc.nextLine();
        Person p1 = new Person(name, phone);
        System.out.println("Enter your Location | " + " Address name" + ", Latitude" + ", Longitude");
        System.out.print("Your Address name : ");
        address = sc.nextLine();
        System.out.print("Latitude : ");
        latitude = sc.nextDouble();
        System.out.print("Longitude : ");
        longtitude = sc.nextDouble();
        sc.nextLine();
        Location r_p1 = new Location(address, latitude, longtitude);
        CustomerAccount cTemp = new CustomerAccount(r_p1, p1);
        cusImp.insert(cTemp);
        System.out.println(cTemp.toString());
        addCustomerlistToArray(getCustomers());
        cusIndex = findForAccount(cTemp.getCusId());
        orderFood();
    }

    private boolean signIn() {
        int id;
        System.out.println("|-----------------" + ConsoleText.BLUE + " Sign in menu" + ConsoleText.BLACK + " -----------------|");
        System.out.print("Enter your customer id :");
        id = sc.nextInt();
        sc.nextLine();
        cusIndex = findForAccount(id);
        if (cusIndex == -1) {
            return false;
        }
        System.out.println(c[cusIndex]);
        orderFood();
        return true;
    }

    private void orderFood() {
        System.out.println("Hello " + c[cusIndex].getMyProfile().getName());
        int menuId;
        do {
            System.out.println(ConsoleText.BLACK + "|-----------------" + ConsoleText.RED + " Order  menu" + ConsoleText.BLACK + " -----------------|");
            System.out.println("(1) Show food menu ");
            System.out.println("(2) Add food to basket ");
            System.out.println("(3) Delete food from basket ");
            System.out.println("(4) See your orderlist ");
            System.out.println("(5) Checkout and get your reciept ");
            System.out.println("(6) Exit out ");
            menuId = sc.nextInt();
            sc.nextLine();
            switch (menuId) {
                case 1:
                    showFoodMenu();
                    break;
                case 2:
                    addFoodIntoBasket();
                    break;
                case 3:
                    deleteFoodFromBasket();
                    break;
                case 4:
                    showOrderlist();
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Please, try again");
            }
        } while (menuId != 6);

    }

    private void showFoodMenu() {
        int menu;
        System.out.println(" (1) Drinks " + " (2) Main dish " + " (3) Dessert ");
        System.out.print("Enter (1-3) to see the menu : ");
        menu = sc.nextInt();
        rs.showFoodMenu(menu);
        System.out.println("-------------------------------------------------");
    }

    private int findForAccount(int id) {
        int accountindex = -1;
        for (int i = 0; i < c.length; i++) {
            if (c[i] != null && c[i].getCusId() == id) {
                return i;
            }
        }
        System.out.println(ConsoleText.RED + "Account not found");
        return accountindex;
    }

    private boolean addFoodIntoBasket() {
        int foodId;
        boolean stopper = true;
        while (stopper) {
            System.out.println("Type the food id you want to add , Type 1 to back to the Order menu");
            foodId = sc.nextInt();
            if (foodId == 1) {
                return false;
            }
            if (!rs.addItemIntoBasket(c[cusIndex], foodId)) {
                System.out.println(ConsoleText.RED + "Food not found");
                continue;
            }
            System.out.println(ConsoleText.GREEN + "Add item success");
        }
        return false;
    }

    private boolean deleteFoodFromBasket() {
        int foodId;
        boolean stopper = true;
        while (stopper) {
            System.out.println("Type the food id you want to delete  , Type 1 to back to the Order menu");
            foodId = sc.nextInt();
            if (foodId == 1) {
                return false;
            }
            if (!rs.delItemFromBasket(c[cusIndex], foodId)) {
                System.out.println(ConsoleText.RED + "Food not found");
                continue;
            }
            System.out.println(ConsoleText.GREEN + "Delete item success");
        }
        return false;

    }

    private void ownerMenu() {
        String textInput;
        do {
            StringBuilder str = new StringBuilder();
            str.append(ConsoleText.BLACK + "-------------------------------------------------");
            str.append('\n' + ConsoleText.RED_UNDERLINED + "\t" + "      Owner  menu".toUpperCase() + ConsoleText.BLACK);
            str.append('\n' + ConsoleText.BLUE + " (1) : Show all customer data  " + '\n');
            str.append(ConsoleText.BLACK + " (2) : Find customer  by id " + '\n');
            str.append(" (3) : Find customer by name " + '\n' + ConsoleText.BLACK);
            str.append(" (4) : Back to homepage " + '\n');
            str.append("Type 1 - 4 here :");
            System.out.print(str);
            textInput = sc.nextLine();
            switch (textInput) {
                case "1":
                    getAllCustomer();
                    break;
                case "2":
                    searchById();
                    break;
                case "3":
                    searchByName();
                    break;
                case "4":
                    break;
            }
        } while (!textInput.equals("4"));
    }

    private void showOrderlist() {
        rs.getMyOrderList(c[cusIndex]);
    }

    private void checkout() {
        rs.checkoutItem(c[cusIndex]);

    }

    private void getAllCustomer() {
        System.out.println(cusImp.getAll());
    }

    private void searchById() {
        System.out.print("customer id : ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println(cusImp.findById(id));
    }

    private void searchByName() {
        System.out.print("customer name : ");
        String name = sc.nextLine();
        System.out.println(cusImp.findByName(name));

    }

    private ArrayList<CustomerAccount> getCustomers() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select * From customers";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ArrayList<CustomerAccount> cuslist = new ArrayList<>();
        while (rst.next()) {
            CustomerAccount cust = new CustomerAccount(rst.getInt("cus_id"), new Person(rst.getString("cus_name"), rst.getString("cus_phone")), new Location(rst.getString("cus_address"), rst.getDouble("latitude"), rst.getDouble("longitude")));
            cuslist.add(cust);
        }
        return cuslist;
    }

    private void addCustomerlistToArray(ArrayList<CustomerAccount> cust) { 
        c = new CustomerAccount[cust.size()];
        for (int i = 0; i < cust.size(); i++) {
            c[i] = cust.get(i);
        }
        if (c == null) {
            System.out.println("add customerlist to array false");
        }
//        if (custA != null) {
//            for (int i = 0; i < custA.length; i++) {
//                System.out.println(custA[i].toString());
//            }
//        }
    }

    private void setValueToRestaurant() throws ClassNotFoundException, SQLException {
        cusImp = new CustomerDBImp();
        resLocation = new Location("SIT EATERY ", 13.652714, 100.493614);
        rs = new Restaurant("SIT EATERY", resLocation);
        ArrayList<Food> foodList = null;
        foodList = rs.getFoods();
        rs.addFoodMenu(foodList);
    }

    public TestRestaurant() {
        this.c = new CustomerAccount[PolicyOrdering.MAX_CUS];
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        TestRestaurant resNew = new TestRestaurant();
        resNew.setValueToRestaurant();
        ArrayList<CustomerAccount> custList = null;
        custList = resNew.getCustomers();
        resNew.addCustomerlistToArray(custList);
        resNew.welcomeMenu();

    }
}
