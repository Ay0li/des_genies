package main;

import main.design_pattern.*;
import java.util.ArrayList;
import java.util.List;

public class ManualTest {
    public static void main(String[] args) {
        Commande commande = new Commande();
        System.out.println("Etat initial de la commande: " + commande.getEtat());
        commande.etatSuivant(); 
        System.out.println("Etat après première transition: " + commande.getEtat());
        commande.etatSuivant(); 
        System.out.println("Etat après deuxième transition: " + commande.getEtat());

        if (!commande.getEtat().equals("livree")) {
            System.out.println("Erreur: l'état de la commande n'est pas 'livree' après deux transitions.");
        } else {
            System.out.println("Succès: l'état de la commande est bien 'livree' après deux transitions.");
        }

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
    }
}
