package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.beans.Transient;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class UnitTests {

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    @Test
    @DisplayName("Good Amount of money after adding and removing some")
    void testAddRemoveMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.addMoney(50);
        assertThat(p.money, is(150));
        p.removeMoney(100);
        assertThat(p.money, is(50));
    }

    @Test
    @DisplayName("Good Amount of money after adding and removing zero")
    void testAddRemoveZeroMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.addMoney(0);
        assertThat(p.money, is(100));
        p.removeMoney(0);
        assertThat(p.money, is(100));
    }

    @Test
    @DisplayName("Verify all player class")
    void testPlayerClass() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("ADVENTURER"));
        p = new player("Florian", "Legolas", "ARCHER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("ARCHER"));
        p = new player("Florian", "Gimli", "DWARF", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("DWARF"));
    }

    @Test
    @DisplayName("Verify XP addition")
    void testAddXp() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p, 50);
        assertThat(p.getXp(), is(50));
    }
    
    @Test
    @DisplayName("Verify end of turn update")
    void testEndOfTurnUpdate() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(41, p.currenthealthpoints);

        p = new player("Florian", "Legolas", "ARCHER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("ARCHER"));
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(41, p.currenthealthpoints);

        p = new player("Florian", "Gimli", "DWARF", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("DWARF"));
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(41, p.currenthealthpoints);

        p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("ADVENTURER"));
        p.healthpoints = 100;
        p.currenthealthpoints = 0;
        UpdatePlayer.majFinDeTour(p);
        

        p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("ADVENTURER"));
        p.healthpoints = 100;
        p.currenthealthpoints = 200;
        UpdatePlayer.majFinDeTour(p);

        p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("ADVENTURER"));
        p.healthpoints = 100;
        p.currenthealthpoints = 70;
        UpdatePlayer.majFinDeTour(p);

        p = new player("Florian", "Gimli", "DWARF", 100, new ArrayList<>(Arrays.asList("Holy Elixir")));
        assertThat(p.getAvatarClass(), is("DWARF"));
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);

        p = new player("Florian", "Legolas", "ARCHER", 100, new ArrayList<>(Arrays.asList("Magic Bow")));
        assertThat(p.getAvatarClass(), is("ARCHER"));
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
    }

    @Test
    @DisplayName("Verify level (New level = Current level) after adding XP")
    void testLevelNoUp() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.retrieveLevel(), is(1));
        UpdatePlayer.addXp(p, 1);
        assertThat(p.retrieveLevel(), is(1));
    }
}
