package de.quele.proxysystem.utils;

import java.util.ArrayList;

public enum Rang {

    Admin("Admin", 9, "§4Admin §8| §4", "693213354311024711"),
    SrStaff("SrStaff", 8, "§cSrStaff §8| §c", "693213357666467962"),
    Staff("Staff", 7, "§cStaff §8| §c", "693213362024349746"),
    Partner("Partner", 6, "§aPartner §8| §a", "693213366818439168"),
    MediaPlus("Media+", 5, "§0Media§4+ §8| §0", "693213367460036618"),
    Media("Media", 4, "§5Media §8| §5", "693213367824810056"),
    Warrior("Warrior", 3, "§dWarrior §8| §d", "693213369934676068"),
    Hyper("Hyper", 2, "§eHyper §8| §d", "693213370597244948"),
    Spieler("Spieler", 1, "§7 §8| §7", "693213371448819834");


    private final String name;
    private final Integer id;
    private final String prefix;
    private final String discordId;

    Rang(String name, int id, String prefix, String discordId) {
        this.name = name;
        this.id = id;
        this.prefix = prefix;
        this.discordId = discordId;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getDiscordId() {
        return discordId;
    }

    public static ArrayList<Rang> getRangs() {
        ArrayList<Rang> rangs = new ArrayList<>();
        rangs.add(Admin);
        rangs.add(SrStaff);
        rangs.add(Staff);
        rangs.add(Partner);
        rangs.add(MediaPlus);
        rangs.add(Media);
        rangs.add(Warrior);
        rangs.add(Hyper);
        rangs.add(Spieler);
        return rangs;
    }

    public static Rang getRangByName(String name) {
        for(Rang rang : getRangs()) {
            if(rang.getName().equalsIgnoreCase(name)) {
                return rang;
            }
        }
        return null;
    }
}
