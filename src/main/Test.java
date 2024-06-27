package main;

import main.design_pattern.*;
import main.process.DBProcess;
import java.util.List;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        // Admin connection test
        DBProcess process = new DBProcess();

        if (!process.getMDP("admin").equals("admin")) {
            System.out.println("Connection failed: invalid user default password");
            System.exit(1);
        }
        else {
            System.out.println("Connection : You have login succesfully");
        }

        process.changerMDP("admin", "admin");

        if (!process.getMDP("admin").equals("admin")) {
            System.out.println("Connection failed: admin password has not changed");
            System.exit(1);
        }
        else {
            System.out.println("Connection : You have login succesfully");
        }

        if (!process.connexion("admin", "admin")) {
            System.out.println("Connection failed: invalid user connection");
            System.exit(1);
        }
        else {
            System.out.println("Connection : You have login succesfully");
        }

        Commande commande = new Commande();
        System.out.println("Initial order state: " + commande.getEtat());
        commande.etatSuivant();
        System.out.println("State after first transition: " + commande.getEtat());
        commande.etatSuivant();
        System.out.println("State after second transition: " + commande.getEtat());

        if (!commande.getEtat().equals("livree")) {
            System.out.println("Order test failed, status not in delivered state");
            System.exit(1);
        } else {
            System.out.println("Success: order state is 'delivered' after two transitions.");
        }


        PizzaHawaienneBuilder pizzaBuilder = new PizzaHawaienneBuilder();
        pizzaBuilder.createNewPizzaProduct();
        pizzaBuilder.buildPate();
        pizzaBuilder.buildSauce();
        pizzaBuilder.buildContenu();
        Pizza pizza = pizzaBuilder.getPizza();
        commande.ajouteProduit(pizza);

        if (commande.getPizzas().contains(pizza)) {
            System.out.println("Success: Hawaiian pizza was added to the order.");
        } else {
            System.out.println("Error: Hawaiian pizza was not added to the order.");
        }

        
        Serveur serveur = new Serveur();
        serveur.setPizzaBuilder(new PizzaNorvegienneBuilder());
        serveur.constructPizza();

        Pizza pizzaFromServer = serveur.getPizza();
        if (pizzaFromServer != null && pizzaFromServer instanceof Pizza) {
            System.out.println("Success: the server created a Norwegian pizza.");
        } else {
            System.out.println("Error: the server did not create a Norwegian pizza.");
        }

        System.out.println("Details of the pizza created by the server: " + pizzaFromServer);

        
        if (!pizzaFromServer.toString().equals("pizza [pate: cooked, sauce: olive oil, contenu: [salmon, mozzarella]]")) {
            System.out.println("Order test failed, pizza is not ready");
            System.exit(1);
        } else {
            System.out.println("Success: the Norwegian pizza was correctly prepared by the server.");
        }

        
        commande.ajouteProduit(pizzaFromServer);
        if (commande.getPizzas().size() == 0) {
            System.out.println("Order test failed, the order did not pick the pizza");
            System.exit(1);
        } else {
            System.out.println("Success: the Norwegian pizza was added to the order.");
        }

        
        commande.etatSuivant(); 
        commande.ajouteProduit(pizzaFromServer);
        if (commande.getPizzas().size() == 2) {
            System.out.println("Order test failed, the order picked the pizza, although it was in Validated status");
            System.exit(1);
        } else {
            System.out.println("Success: no pizza was added after the order was validated.");
        }
    }
}
