package com.school.hei.ratiocinator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Affirmation {
    private String contenu;
    
    public String calucle() {
        if (contenu.contains(" Donc ")) {
            String[] elements = contenu.split(" Donc ");
            if (elements[0].contains(" et ") || elements[0].contains(" ou ")) {
                String elem1 = new Affirmation(elements[0]).calucle();
                String elem2 = new Affirmation(elements[1]).calucle();
                if (elem1.equals("vrai") && (elem1.equals(elem2))) return "vrai";
                if (elem1.equals("faux") && (elem1.equals(elem2))) return "faux";
                if (!elem1.equals(elem2)) return "faux";
            }
            if (elements[0].equals("vrai") && elements[1].equals("faux")) return "faux";
            if (elements[0].equals("vrai") && elements[1].equals("vrai")) return "vrai";
        }

        if (contenu.contains(" et ")) {
            String[] elements = contenu.split(" et ");
            if ((verifierAffirmation(elements[0]) != null || verifierAffirmation(elements[1]) != null) &&
                (verifierAffirmation(elements[0]).equals("vrai") && verifierAffirmation(elements[1]).equals("vrai"))) return "vrai";
            return "faux";
        }

        if (contenu.contains(" ou ")) {
            String[] elements = contenu.split(" ou ");
            if ((verifierAffirmation(elements[0]) != null || verifierAffirmation(elements[1]) != null) &&
                (verifierAffirmation(elements[0]).equals("vrai") || verifierAffirmation(elements[1]).equals("vrai"))) return "vrai";
            return "faux";
        }

        return verifierAffirmation(contenu) ;
    }

    private String verifierAffirmation(String affirmation) {
        affirmation = affirmation.replace(".", "");
        if (recuperationDesAffirmation().containsKey(affirmation))
            return recuperationDesAffirmation().get(affirmation);
        return "jenesaispas";
    }

    private static Map<String, String> recuperationDesAffirmation() {
        try(BufferedReader lecteur = new BufferedReader(new FileReader("Affirmations.txt"));) {
            Map<String, String> donnees = new LinkedHashMap<>();
            String ligne;
            while ((ligne = lecteur.readLine()) != null) {
                String[] parties = ligne.split("=");
                String cle = parties[0].trim();
                String valeur = parties[1].trim();
                cle = cle.replace(".", "");
                valeur = valeur.toLowerCase();
                donnees.put(cle, valeur);
            }
            lecteur.close();
            return donnees;
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
        return null;
    }
}
