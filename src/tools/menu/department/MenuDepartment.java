package tools.menu.department;

import daos.DepartmentDAO;
import daos.LocationDAO;
import models.Department;
import models.Location;
import tools.DbConnection;
import java.util.Scanner;

public class MenuDepartment {

    static DbConnection conn = new DbConnection();
    static DepartmentDAO dDao = new DepartmentDAO(conn.getConnection());

    public void checkConnection() {
        if(conn.isConnectionStatus()) {
            menuDepartment();
        } else {
            System.out.println("\n\n===========================================");
            System.out.println("System can't connect to the database");
            System.out.println("===========================================");
        }
    }

    public static void menuDepartment() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Menu CRUD Locations");
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
                insertDepartment();
                break;

            case 3:
                updateDepartment();
                break;

            case 4:
                deleteDepartment();
                break;
        }
    }

    public static void showAll() {
        for (Department department : dDao.getAll()) {
            System.out.println(department.toString());
        }
    }

    public static void insertDepartment() {

        Scanner myObj = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);
        System.out.print("Insert an ID: ");
        int departId = myObj2.nextInt();
        System.out.print("Insert a department name : ");
        String departName = myObj.nextLine();
        System.out.print("Insert manager key : ");
        int managerKey = myObj2.nextInt();
        System.out.print("Insert location key : ");
        int locationKey = myObj2.nextInt();


        try {
            Department department = new Department(departId, departName, managerKey, locationKey);
            boolean hasil = dDao.insert(department);
            //dikasih if == true buat ngecek validasi
            if(hasil) {
                System.out.println("\n\nSuccessfully inserted a new departmen record");
                System.out.println("Inserted data : \n" + department.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

    public static void updateDepartment() {
        Scanner myObj = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);
        System.out.print("\n\nInput Id you want to update : ");
        int currId = myObj2.nextInt();
        System.out.println("Original Data: ");
        System.out.println(dDao.getById(currId).toString());

        System.out.print("\nInsert an ID: ");
        int departId = myObj2.nextInt();
        System.out.print("Insert a department name : ");
        String departName = myObj.nextLine();
        System.out.print("Insert manager key : ");
        int managerKey = myObj2.nextInt();
        System.out.print("Insert location key : ");
        int locationKey = myObj2.nextInt();

        try {
            Department department = new Department(departId, departName, managerKey, locationKey);
            boolean hasil = dDao.update(department, currId);
            if (hasil) {
                System.out.println("\n\nSuccessfully updated a department record");
                System.out.println("Updated data : ");
                System.out.println(department.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deleteDepartment() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("\n\nInput Id you want to delete : ");
        int deletedRecord = myObj.nextInt();
        System.out.println("The Data with ID(" + deletedRecord + ") : ");
        System.out.println(dDao.getById(deletedRecord).toString());
        try {
            boolean hasil = dDao.delete(deletedRecord);
            if (hasil) {
                System.out.println("\n\nSuccessfully deleted a deparment record");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
