package tools;


import tools.menu.MenuUtama;

public class Main {
    public static void main(String[] args) {
//        DbConnection conn =  new DbConnection();
//        System.out.println(conn.getConnection());

        MenuUtama menu = new MenuUtama();
        menu.menuUtama();
    }
}
