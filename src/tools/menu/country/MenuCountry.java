package tools.menu.country;

import daos.CountryDAO;
import models.Country;
import tools.DbConnection;

import java.util.Scanner;

public class MenuCountry {
    static DbConnection conn = new DbConnection();
    static CountryDAO cdao = new CountryDAO(conn.getConnection());

    public void checkConnection() {
        if(conn.isConnectionStatus()) {
            menuCountry();
        } else {
            System.out.println("\n\n===========================================");
            System.out.println("System can't connect to the database");
            System.out.println("===========================================");
        }
    }

    public static void menuCountry() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Menu CRUD Country");
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
                insertCountry();
                break;

            case 3:
                updateCountry();
                break;

            case 4:
                deleteCountry();
                break;
        }
    }

    public static void showAll() {
        int i = 1;
        for (Country country : cdao.getAll()) {
            System.out.println(i + ". " + country.toString());
            ++i;
        }
    }

    public static void insertCountry() {
        Scanner myObj = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);
        System.out.print("Insert countryID : ");
        String countryId = myObj.nextLine();
        System.out.print("Insert country name : ");
        String cName = myObj.nextLine();
        System.out.print("Insert region id : ");
        int regId = myObj2.nextInt();
        try {
            Country country = new Country(countryId, cName, regId);
            boolean hasil = cdao.insert(country);
            //dikasih if == true buat validasi
            if(hasil) { //if(hasil) adalah simplify dari if (hasil == true)
                System.out.println("\n\nSuccessfully inserted a new country");
                System.out.println("Inserted data : \n" + country.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void updateCountry() {
        Scanner myObj = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);
        System.out.print("\n\nInput Id you want to update : ");
        String id = myObj.nextLine();
        System.out.println("Original Data: ");
        System.out.println(cdao.getById(id).toString());
        System.out.print("\nInput the new ID of the new country : ");
        String newId = myObj.nextLine();
        System.out.print("Input new country name : ");
        String newCname = myObj2.nextLine();
        try {
            Country country = new Country(newId, newCname, cdao.getById(id).getCountryRegion());
            boolean hasil = cdao.update(country, id);
            if (hasil) { //if(hasil) adalah simplify dari if (hasil == true)
                System.out.println("\n\nSuccessfully updated a country record");
                System.out.println("Updated data : ");
                System.out.println(country.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deleteCountry() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("\n\nInput Id you want to delete : ");
        String deletedRecord = myObj.nextLine();
        System.out.println("The Data with ID(" + deletedRecord + ") : ");
        System.out.println(cdao.getById(deletedRecord).toString());
        try {
            boolean hasil = cdao.delete(deletedRecord);
            if (hasil) { //if(hasil) adalah simplify dari if (hasil == true)
                System.out.println("\n\nSuccessfully deleted a country record");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }




}
