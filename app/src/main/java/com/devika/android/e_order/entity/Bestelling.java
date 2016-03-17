package com.devika.android.e_order.entity;

/**
 * Created by Dion on 3/16/2016.
 */
public class Bestelling {

    private static String bestelling_drank;
    private static String bestelling_eten;
    private static String username;
    private static long id;

    public Bestelling(long id, String username, String bestelling_drank, String bestelling_eten) {

        Bestelling.id = id;
        Bestelling.username = username;
        Bestelling.bestelling_drank = bestelling_drank;
        Bestelling.bestelling_eten = bestelling_eten;
    }


    public static String getBestelling() {
        return bestelling_drank + " een " + bestelling_eten;
    }

    public static String getBestelling_drank() {
        return bestelling_drank;
    }

    public static void setBestelling_drank(String bestelling_drank) {
        Bestelling.bestelling_drank = bestelling_drank;
    }

    public static String getBestelling_eten() {
        return bestelling_eten;
    }

    public static void setBestelling_eten(String bestelling_eten) {
        Bestelling.bestelling_eten = bestelling_eten;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Bestelling.username = username;
    }

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
        Bestelling.id = id;
    }
}
