package re.forestier.edu.rpg;

public class Affichage {
    
    public static String afficherJoueur(player player) {
        final String[] finalString = {"Joueur " + player.Avatar_name + " joué par " + player.playerName};
        finalString[0] += "\nClasse : " + player.getAvatarClass();
        finalString[0] += "\nArgent : " + player.money + " pièces d'or";
        finalString[0] += "\nPoints de vie : " + player.currenthealthpoints + "/" + player.healthpoints;
        finalString[0] += "\nNiveau : " + player.retrieveLevel() + " (XP totale : " + player.xp + ")";
        finalString[0] += "\n\nCapacités :";
        player.abilities.forEach((name, level) -> {
            finalString[0] += "\n   " + name + " : " + level;
        });
        finalString[0] += "\n\nInventaire :";
        player.inventory.forEach(item -> {
            finalString[0] += "\n   " + item;
        });

        return finalString[0];
    }

    public static String afficherJoueurMarkdown(player player) {
        StringBuilder markdownString = new StringBuilder();
        markdownString.append("# Joueur ").append(player.Avatar_name).append(" joué par ").append(player.playerName).append("\n");
        markdownString.append("## Classe : ").append(player.getAvatarClass()).append("\n");
        markdownString.append("**Argent :** ").append(player.money).append(" pièces d'or\n");
        markdownString.append("**Points de vie :** ").append(player.currenthealthpoints).append("/").append(player.healthpoints).append("\n");
        markdownString.append("**Niveau :** ").append(player.retrieveLevel()).append(" (XP totale : ").append(player.xp).append(")\n");
        markdownString.append("\n## Capacités :\n");
        player.abilities.forEach((name, level) -> {
            markdownString.append("* ").append(name).append(" : ").append(level).append("\n");
        });
        markdownString.append("\n## Inventaire :\n");
        player.inventory.forEach(item -> {
            markdownString.append("* ").append(item).append("\n");
        });

        return markdownString.toString();
    }
}