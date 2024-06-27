package main.design_pattern;

import java.util.List;
import java.util.ArrayList;

public class Pizza implements Ingredient {
    private Pate pate;
    private Sauce sauce;
    private List<Contenu> contenu = new ArrayList<>();


    public void setPate(Pate pate) {
        this.pate = pate;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public void addContenu(Contenu contenu) {
        this.contenu.add(contenu);
    }

    public void removeContenu(Contenu contenu) {
        this.contenu.remove(contenu);
    }

    @Override
    public String toString() {
        return "pizza [pate: " + pate.toString() + ", sauce: " + sauce.toString() + ", contenu: " + contenu.toString() + "]";
    }


    @Override
    public double getPrix() {
        double contenu_somme = 0.0;
        for (Contenu ct : contenu) {
            contenu_somme += ct.getPrix();
        }
        return pate.getPrix() + sauce.getPrix() + contenu_somme;
    }
}