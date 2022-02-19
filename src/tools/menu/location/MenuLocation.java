package tools.menu.location;

import daos.LocationDAO;
import models.Location;
import tools.DbConnection;
import java.util.Scanner;


public class MenuLocation {

    static DbConnection conn = new DbConnection();
    static LocationDAO ldao = new LocationDAO(conn.getConnection());

    public void checkConnection() {
        if(conn.isConnectionStatus()) {
            menuLocation();
        } else {
            System.out.println("\n\n===========================================");
            System.out.println("System can't connect to the database");
            System.out.println("===========================================");
        }
    }

    public static void menuLocation() {
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
                insertLoc();
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
        for (Location loc : ldao.getAll()) {
            System.out.println(loc.toString());
        }
    }

    public static void insertLoc() {

        Scanner myObj = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);
        System.out.print("Insert an ID: ");
        int id = myObj2.nextInt();
        System.out.print("Insert street address : ");
        String alamat = myObj.nextLine();
        System.out.print("Insert postal code : ");
        String kodePos = myObj.nextLine();
        System.out.print("Insert city name : ");
        String city = myObj.nextLine();
        System.out.print("Insert state province : ");
        String provinsi = myObj.nextLine();
        System.out.print("Insert country name : ");
        String country = myObj.nextLine();

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

    public static void updateLoc(){
        Scanner myObj = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);
        System.out.print("\n\nInput Id you want to update : ");
        int newId = myObj.nextInt();
        System.out.println("Original Data: ");
        System.out.println(ldao.getById(newId).toString());

        System.out.print("Insert street address : ");
        String alamat = myObj2.nextLine();
        System.out.print("Insert postal code : ");
        String kodePos = myObj2.nextLine();
        System.out.print("Insert city name : ");
        String city = myObj2.nextLine();
        System.out.print("Insert state province : ");
        String provinsi = myObj2.nextLine();
        System.out.print("Insert country name : ");
        String country = myObj2.nextLine();

        try {
            Location loc = new Location(newId, alamat, kodePos, city, provinsi, country);
            boolean hasil = ldao.update(loc);
            if (hasil) {
                System.out.println("\n\nSuccessfully updated a location");
                System.out.println("Updated data : ");
                System.out.println(loc.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deleteLoc() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("\n\nInput Id you want to delete : ");
        int deletedRecord = myObj.nextInt();
        System.out.println("The Data with ID(" + deletedRecord + ") : ");
        System.out.println(ldao.getById(deletedRecord).toString());
        try {
            boolean hasil = ldao.delete(deletedRecord);
            if (hasil) {
                System.out.println("\n\nSuccessfully deleted a location");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
