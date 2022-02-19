package tools.menu.job;


import daos.JobDAO;
import models.Job;
import tools.DbConnection;

import java.util.Scanner;

public class MenuJob {
    static DbConnection conn = new DbConnection();
    static JobDAO jdao = new JobDAO(conn.getConnection());

    public void checkConnection() {
        if(conn.isConnectionStatus()) {
            menuJob();
        } else {
            System.out.println("\n\n===========================================");
            System.out.println("System can't connect to the database");
            System.out.println("===========================================");
        }
    }

    public static void menuJob() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Menu CRUD Job");
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
                insertJob();
                break;

            case 3:
                updateJob();
                break;

            case 4:
                deleteJob();
                break;
        }
    }

    private static void showAll() {
        int i = 1;
        for (Job job : jdao.getAll()) {
            System.out.println(i + ". " + job.toString());
            ++i;
        }
    }

    private static void insertJob() {
        Scanner myObj = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);
        System.out.print("Insert job id : ");
        String jobId = myObj.nextLine();
        System.out.print("Insert job title : ");
        String jobTitle = myObj.nextLine();
        System.out.print("Insert minimum salary : ");
        Double minSalary = myObj2.nextDouble();
        System.out.print("Insert maximum salary : ");
        Double maxSalary = myObj2.nextDouble();
        try {
            Job job = new Job(jobId, jobTitle, minSalary, maxSalary);
            boolean hasil = jdao.insert(job);
            //dikasih if == true buat validasi
            if(hasil) { //if(hasil) adalah simplify dari if (hasil == true)
                System.out.println("\n\nSuccessfully inserted a new job record");
                System.out.println("Inserted data : \n" + job.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateJob() {
        Scanner myObj = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);
        System.out.print("\n\nInput Id you want to update : ");
        String jobId = myObj.nextLine();
        System.out.print("Insert job id : ");
        String newId = myObj.nextLine();
        System.out.print("Insert job title : ");
        String jobTitle = myObj.nextLine();
        System.out.print("Insert minimum salary : ");
        Double minSalary = myObj2.nextDouble();
        System.out.println("Insert maximum salary : ");
        Double maxSalary = myObj2.nextDouble();
        try {
            Job job = new Job(newId, jobTitle, minSalary, maxSalary);
            boolean hasil = jdao.update(job, jobId);
            if (hasil==true) { //if(hasil) adalah simplify dari if (hasil == true)
                System.out.println("\n\nSuccessfully updated a job record");
                System.out.println("Updated data : ");
                System.out.println(job.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteJob() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("\n\nInput Id you want to delete : ");
        String deletedRecord = myObj.nextLine();
        System.out.println("The Data with ID(" + deletedRecord + ") : ");
        System.out.println(jdao.getById(deletedRecord).toString());
        try {
            boolean hasil = jdao.delete(deletedRecord);
            if (hasil) { //if(hasil) adalah simplify dari if (hasil == true)
                System.out.println("\n\nSuccessfully deleted a country record");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
