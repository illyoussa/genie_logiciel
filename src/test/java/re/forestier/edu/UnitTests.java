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
    @DisplayName("Remove all money")
    void testRemoveAllMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.removeMoney(100);
        assertThat(p.money, is(0));
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
    @DisplayName("Verify XP addition and remove")
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
        assertEquals(0, p.currenthealthpoints);

        p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("ADVENTURER"));
        p.healthpoints = 100;
        p.currenthealthpoints = 200;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(100, p.currenthealthpoints);

        p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("ADVENTURER"));
        p.healthpoints = 100;
        p.currenthealthpoints = 70;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(70, p.currenthealthpoints);

        p = new player("Florian", "Gimli", "DWARF", 100, new ArrayList<>(Arrays.asList("Holy Elixir")));
        assertThat(p.getAvatarClass(), is("DWARF"));
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(42, p.currenthealthpoints);

        p = new player("Florian", "Legolas", "ARCHER", 100, new ArrayList<>(Arrays.asList("Magic Bow")));
        assertThat(p.getAvatarClass(), is("ARCHER"));
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(45, p.currenthealthpoints);

        p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("ADVENTURER"));
        UpdatePlayer.addXp(p, 500);
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(42, p.currenthealthpoints);

        p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("ADVENTURER"));
        UpdatePlayer.addXp(p, 27);
        p.healthpoints = 100;
        p.currenthealthpoints = 40;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(42, p.currenthealthpoints);

        p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("ADVENTURER"));
        p.healthpoints = 100;
        p.currenthealthpoints = 50;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(50, p.currenthealthpoints);

        p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("ADVENTURER"));
        p.healthpoints = 100;
        p.currenthealthpoints = 100;
        UpdatePlayer.majFinDeTour(p);
        assertEquals(100, p.currenthealthpoints);
    }

    @Test
    @DisplayName("Verify level (New level = Current level) after adding XP")
    void testLevelNoUp() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.retrieveLevel(), is(1));
        UpdatePlayer.addXp(p, 1);
        assertThat(p.retrieveLevel(), is(1));
        assertThat(UpdatePlayer.addXp(p, 1), is(false));
    }

    @Test
    @DisplayName("Verify level (New level > Current level) after adding XP")
    void testLevelUp() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.retrieveLevel(), is(1));
        UpdatePlayer.addXp(p, 20);
        assertThat(p.retrieveLevel(), is(2));
        assertThat(UpdatePlayer.addXp(p, 20), is(true));
    }

    @Test
    @DisplayName("Other class")
    void OtherClass() {
        player p = new player("Maxou", "MaxMax", "DEMON", 100, new ArrayList<>());
    }

    @Test
    @DisplayName("Verify all levels")
    void testAllLevels() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.retrieveLevel(), is(1));
        UpdatePlayer.addXp(p, 10);
        assertThat(p.retrieveLevel(), is(2));
        UpdatePlayer.addXp(p, 17);
        assertThat(p.retrieveLevel(), is(3));
        UpdatePlayer.addXp(p, 30);
        assertThat(p.retrieveLevel(), is(4));
        UpdatePlayer.addXp(p, 54);
        assertThat(p.retrieveLevel(), is(5));
    }

    @Test
    @DisplayName("Verify add xp with a inventory")
    void testAddXpWithInventory() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>(Arrays.asList("Magic Bow")));
        assertThat(p.retrieveLevel(), is(1));
        UpdatePlayer.addXp(p, 10);
        assertThat(p.retrieveLevel(), is(2));
    }

    @Test
    @DisplayName("Verify abilities at ADVENTURER all levels")
    void testAbilitiesLevel1() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.retrieveLevel(), is(1));
        assertThat(p.abilities.get("INT"), is(1));
        assertThat(p.abilities.get("DEF"), is(1));
        assertThat(p.abilities.get("ATK"), is(3));
        assertThat(p.abilities.get("CHA"), is(2));

        UpdatePlayer.addXp(p, 10);
        assertThat(p.retrieveLevel(), is(2));
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("DEF"), is(1));
        assertThat(p.abilities.get("ATK"), is(3));
        assertThat(p.abilities.get("CHA"), is(3));

        UpdatePlayer.addXp(p, 17);
        assertThat(p.retrieveLevel(), is(3));
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("DEF"), is(1));
        assertThat(p.abilities.get("ATK"), is(5));
        assertThat(p.abilities.get("CHA"), is(3));
        assertThat(p.abilities.get("ALC"), is(1));

        UpdatePlayer.addXp(p, 30);
        assertThat(p.retrieveLevel(), is(4));
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("DEF"), is(3));
        assertThat(p.abilities.get("ATK"), is(5));
        assertThat(p.abilities.get("CHA"), is(3));
        assertThat(p.abilities.get("ALC"), is(1));

        UpdatePlayer.addXp(p, 54);
        assertThat(p.retrieveLevel(), is(5));
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("DEF"), is(4));
        assertThat(p.abilities.get("ATK"), is(5));
        assertThat(p.abilities.get("CHA"), is(3));
        assertThat(p.abilities.get("ALC"), is(1));
        assertThat(p.abilities.get("VIS"), is(1));
    }
}