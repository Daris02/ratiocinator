package ratiocinator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.school.hei.ratiocinator.Affirmation;

public class AffirmationTest {
    
    @Test
    void affirmation_vrai() {
        var veriter_1 = new Affirmation("Lou est beau.");
        System.out.println(veriter_1.calucle());
        assertEquals("vrai", veriter_1.calucle());

    }

    @Test
    void affirmation_mensonge() {
        var mensonge_1 = new Affirmation("Lou est pauvre.");
        assertEquals("faux", mensonge_1.calucle());
    }

    @Test
    void affirmation_multiple() {
        var affirmation_1 = new Affirmation("Lou est pauvre et Lou est généreux.");
        var affirmation_2 = new Affirmation("Lou est beau. Donc Lou est pauvre.");
        var affirmation_3 = new Affirmation("Lou est pauvre. Donc Lou est généreux.");
        var affirmation_4 = new Affirmation("Lou est beau ou Lou est généreux. Donc Lou est pauvre.");
        
        assertEquals("faux", affirmation_1.calucle());
        assertEquals("jenesaispas", affirmation_2.calucle());
        assertEquals("jenesaispas", affirmation_3.calucle());
        assertEquals("faux", affirmation_4.calucle());
    }

    @Test
    void affirmation_different() {
        var affirmation = new Affirmation("Le ciel est bleu.");
        assertEquals("vrai", affirmation.calucle());
    }

    @Test
    void affirmation_inconnue() {
        var affirmation = new Affirmation("Mon code marche");
        assertEquals("jenesaispas", affirmation.calucle());
    }
}
