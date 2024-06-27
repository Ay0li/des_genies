package main;

import main.design_pattern.*;
import main.process.DBProcess;
import java.util.List;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        // Test de la connexion admin
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

        // Test changement d'état de la commande
        Commande commande = new Commande();
        System.out.println("Etat initial de la commande: " + commande.getEtat());
        commande.etatSuivant();
        System.out.println("Etat après première transition: " + commande.getEtat());
        commande.etatSuivant();
        System.out.println("Etat après deuxième transition: " + commande.getEtat());

        if (!commande.getEtat().equals("livree")) {
            System.out.println("commande test failed, status not in livree state");
            System.exit(1);
        } else {
            System.out.println("Succès: l'état de la commande est bien 'livree' après deux transitions.");
        }

        // Test ajout de pizza à la commande
        PizzaHawaienneBuilder pizzaBuilder = new PizzaHawaienneBuilder();
        pizzaBuilder.createNewPizzaProduct();
        pizzaBuilder.buildPate();
        pizzaBuilder.buildSauce();
        pizzaBuilder.buildContenu();
        Pizza pizza = pizzaBuilder.getPizza();
        commande.ajouteProduit(pizza);

        if (commande.getPizzas().contains(pizza)) {
            System.out.println("Succès: la pizza hawaienne a bien été ajoutée à la commande.");
        } else {
            System.out.println("Erreur: la pizza hawaienne n'a pas été ajoutée à la commande.");
        }

        // Test utilisation du serveur pour créer une pizza norvégienne
        Serveur serveur = new Serveur();
        serveur.setPizzaBuilder(new PizzaNorvegienneBuilder());
        serveur.constructPizza();

        Pizza pizzaFromServer = serveur.getPizza();
        if (pizzaFromServer != null && pizzaFromServer instanceof Pizza) {
            System.out.println("Succès: le serveur a bien créé une pizza norvégienne.");
        } else {
            System.out.println("Erreur: le serveur n'a pas créé une pizza norvégienne.");
        }

        System.out.println("Détails de la pizza créée par le serveur: " + pizzaFromServer);

        // Vérification des détails de la pizza norvégienne créée par le serveur
        if (!pizzaFromServer.toString().equals("pizza [pate: cuite, sauce: Huile d'olive, contenu: [saumon, mozzarella]]")) {
            System.out.println("commande test failed, pizza is not ready");
            System.exit(1);
        } else {
            System.out.println("Succès: la pizza norvégienne a été correctement préparée par le serveur.");
        }

        // Ajout de la pizza à la commande et vérification
        commande.ajouteProduit(pizzaFromServer);
        if (commande.getPizzas().size() == 0) {
            System.out.println("commande test failed, command was not picking the pizza");
            System.exit(1);
        } else {
            System.out.println("Succès: la pizza norvégienne a bien été ajoutée à la commande.");
        }

        // Test ajout de pizza après validation de la commande
        commande.etatSuivant(); // passe à "validée"
        commande.ajouteProduit(pizzaFromServer);
        if (commande.getPizzas().size() == 2) {
            System.out.println("commande test failed, command was picking the pizza, although it was in Validee status");
            System.exit(1);
        } else {
            System.out.println("Succès: aucune pizza n'a été ajoutée après validation de la commande.");
        }
    }
}
