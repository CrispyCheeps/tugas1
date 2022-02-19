package tools.menu;

import tools.menu.country.MenuCountry;
import tools.menu.job.MenuJob;
import tools.menu.location.MenuLocation;
import tools.menu.region.MenuRegion;
import java.util.Scanner;

public class MenuUtama {
    Scanner myObj = new Scanner(System.in);

    public static void ClearConsole(){
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void menuUtama () {
        System.out.println("----------Welcome!----------");
        System.out.println("\n\t\tCRUD PROJECT");

        System.out.println("\nTable menu");
        System.out.println("1. Region");//clear (mgkn bisa sedikit direvisi)
        System.out.println("2. Country");//clear
        System.out.println("3. Location");//clear
        System.out.println("4. Department");
        System.out.println("5. Job");//clear
        System.out.println("6. Employee");
        System.out.print("Masukkan pilihan menu: ");
        int pilTableMenu = myObj.nextInt();
        System.out.println(pilTableMenu);
        switch (pilTableMenu) {
            case 1:
                ClearConsole();
                MenuRegion pil1 = new MenuRegion();
                pil1.checkConnection();
                break;

            case 2:
                ClearConsole();
                MenuCountry pil2 = new MenuCountry();
                pil2.checkConnection();
                break;

            case 3:
                ClearConsole();
                MenuLocation pil3 = new MenuLocation();
                pil3.checkConnection();
                break;

            case 4 :
                ClearConsole();
//                MenuJob pil4 = new MenuJob();
//                pil4.checkConnection();
                break;

            case 5:
                ClearConsole();
                MenuJob pil5 = new MenuJob();
                pil5.checkConnection();
        }

    }


}


