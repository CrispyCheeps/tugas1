package tools.menu.region;

import daos.RegionDAO;
import models.Region;
import tools.DbConnection;

import java.util.Scanner;

public class MenuRegion {

    static DbConnection conn = new DbConnection();
    static RegionDAO rdao = new RegionDAO(conn.getConnection());

    public void checkConnection() {
        if(conn.isConnectionStatus()) {
            menuRegion();
        } else {
            System.out.println("\n\n===========================================");
            System.out.println("System can't connect to the database");
            System.out.println("===========================================");
        }
    }

    public static void menuRegion() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Menu CRUD Region");
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
                insertRegionName();
                break;

            case 3:
                updateRegionName();
                break;

            case 4:
                deleteRegion();
                break;
        }
    }

    public static void showAll() {
        int i = 1;
        for (Region region : rdao.getAll()) {
            System.out.println(i + ". Id :" + region.getRegionId() + ", " +
                    "Region Name : " + region.getRegionName());
            ++i;
        }
    }

    public static void insertRegionName() {

        Scanner myObj = new Scanner(System.in);
        System.out.print("Insert a name of region : ");
        String regionName = myObj.nextLine();
        try {
            Region region = new Region(0, regionName);
            boolean hasil = rdao.insert(region);
            //dikasih if == true buat validasi
            if(hasil) { //if(hasil) adalah simplify dari if (hasil == true)
                System.out.println("\n\nSuccessfully inserted a new region name");
                System.out.println("Inserted data : " + region.getRegionName());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void updateRegionName() {
        Scanner myObj = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);
        System.out.print("\n\nInput Id you want to update : ");
        int newId = myObj.nextInt();
        System.out.println("Original Data: ");
        System.out.println("ID\t\t: " + rdao.getById(newId).getRegionId());
        System.out.println("Region name\t:" + rdao.getById(newId).getRegionName());
        System.out.print("\nInput new region name :");
        String newRegionName = myObj2.nextLine();

        try {
            Region region = new Region(newId, newRegionName);
            boolean hasil = rdao.update(region);
            if (hasil) { //if(hasil) adalah simplify dari if (hasil == true)
                System.out.println("\n\nSuccessfully updated a new region name");
                System.out.println("Updated data : ");
                System.out.println("Id : " + newId + ", " +
                        "new Region Name: " + newRegionName);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deleteRegion() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("\n\nInput Id you want to delete : ");
        int deletedRecord = myObj.nextInt();
        System.out.println("The Data with ID(" + deletedRecord + ") : ");
        System.out.println("ID\t\t: " + rdao.getById(deletedRecord).getRegionId());
        System.out.println("Region name\t:" + rdao.getById(deletedRecord).getRegionName());
        try {
            boolean hasil = rdao.delete(deletedRecord);
            if (hasil) { //if(hasil) adalah simplify dari if (hasil == true)
                System.out.println("\n\nSuccessfully deleted a new region name");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
