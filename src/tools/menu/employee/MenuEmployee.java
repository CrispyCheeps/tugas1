package tools.menu.employee;

import daos.EmployeeDAO;
import models.Employee;
import models.Location;
import tools.DbConnection;
import java.util.Scanner;

public class MenuEmployee {

    static DbConnection conn = new DbConnection();
    static EmployeeDAO edao = new EmployeeDAO(conn.getConnection());

    public void checkConnection() {
        if(conn.isConnectionStatus()) {
            menuEmployee();
        } else {
            System.out.println("\n\n===========================================");
            System.out.println("System can't connect to the database");
            System.out.println("===========================================");
        }
    }

    public static void menuEmployee() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Menu CRUD Employees");
        System.out.println("1. Show all elements");
        System.out.println("2. Insert element");
        System.out.println("3. Update element");
        System.out.println("4. Delete element");
        System.out.print("Enter menu: ");
        int pilMenu = myObj.nextInt();
        switch (pilMenu) {
            case 1:
                showAll();
                break;

            case 2:
                insertEmployee();
                break;

            case 3:
                updateLoc();
                break;

            case 4:
                deleteLoc();
                break;
        }
    }

    public static void showAll() {
        for (Employee emp : edao.getAll()) {
            System.out.println(emp.toString());
        }
    }

    public static void insertEmployee() {

        Scanner myObj = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);
        System.out.print("Insert an ID: ");
        int id = myObj.nextInt();
        System.out.print("Insert first name : ");
        String fName = myObj2.nextLine();
        System.out.print("Insert last name : ");
        String lName = myObj2.nextLine();
        System.out.print("Insert email : ");
        String email = myObj2.nextLine();
        System.out.print("Insert phone number : ");
        String noTelp = myObj2.nextLine();
        System.out.print("Insert hire date : ");
        String hireDate = myObj2.nextLine();
        System.out.println("Insert ");

        try {
            Location loc = new Location(id, alamat, kodePos, city, provinsi, country);
            boolean hasil = ldao.insert(loc);
            //dikasih if == true buat ngecek validasi
            if(hasil) {
                System.out.println("\n\nSuccessfully inserted a new location");
                System.out.println("Inserted data : \n" + loc.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
    }
}
