package main;

import main.process.DBProcess;

public class Test {
    public static void main(String[] args) {
        DBProcess process = new DBProcess();

        if (!process.getMDP("admin").equals("admin")) {
            System.out.println("Connection failed: invalid user default password");
            System.exit(1);
        }

        process.changerMDP("admin", "admin");

        if (!process.getMDP("admin").equals("admin")) {
            System.out.println("Connection failed: admin password has not changed");
            System.exit(1);
        }

        if (!process.connexion("admin", "admin")) {
            System.out.println("Connection failed: invalid user connection");
            System.exit(1);
        }
    }
}