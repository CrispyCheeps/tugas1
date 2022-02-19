package tools.menu.employee;

import daos.EmployeeDAO;
import models.Employee;
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
                updateEmployee();
                break;

            case 4:
                deleteEmployee();
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
        Scanner myObj3 = new Scanner(System.in);
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
        System.out.println("Insert the job code: ");
        String jobCode = myObj2.nextLine();
        System.out.print("Insert salary : ");
        Double salary = myObj3.nextDouble();
        System.out.print("Insert the comission : ");
        Double comission = myObj3.nextDouble();
        System.out.print("Insert the manager code : ");
        int manager = myObj.nextInt();
        System.out.print("Insert department code : ");
        int department = myObj.nextInt();

        try {
            Employee emp = new Employee(id, fName, lName, email, noTelp, hireDate, jobCode, salary,
                    comission, manager, department);
            boolean hasil = edao.insert(emp);
            //dikasih if == true buat ngecek validasi
            if(hasil) {
                System.out.println("\n\nSuccessfully inserted a new location");
                System.out.println("Inserted data : \n" + emp.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

    public static void updateEmployee(){
        Scanner myObj = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);
        Scanner myObj3 = new Scanner(System.in);

        System.out.print("\n\nInput Id you want to update : ");
        int id = myObj.nextInt();
        System.out.println("Original Data: ");
        System.out.println(edao.getById(id).toString());

        System.out.print("Insert first name : ");
        String fName = myObj2.nextLine();
        System.out.print("Insert last name : ");
        String lName = myObj2.nextLine();
        System.out.print("Insert phone number : ");
        String noTelp = myObj2.nextLine();
        System.out.print("Insert hire date : ");
        String hireDate = myObj2.nextLine();
        System.out.println("Insert salary : ");
        Double salary = myObj3.nextDouble();
        System.out.println("Insert the comission : ");
        Double comission = myObj3.nextDouble();

        try {
            Employee emp = new Employee(id, fName, lName, edao.getById(id).getEmail(),
                    noTelp, hireDate, edao.getById(id).getJob(), salary,
                    comission, edao.getById(id).getManager(), edao.getById(id).getDepartment());
            boolean hasil = edao.update(emp);
            if (hasil) {
                System.out.println("\n\nSuccessfully updated an employee record");
                System.out.println("Updated data : ");
                System.out.println(emp.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deleteEmployee() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("\n\nInput Id you want to delete : ");
        int deletedRecord = myObj.nextInt();
        System.out.println("The Data with ID(" + deletedRecord + ") : ");
        System.out.println(edao.getById(deletedRecord).toString());
        try {
            boolean hasil = edao.delete(deletedRecord);
            if (hasil) {
                System.out.println("\n\nSuccessfully deleted an employee record");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
