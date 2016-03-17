package com.devika.android.e_order.entity;

/**
 * Created by devika on 3/15/2016.
 */
public class User {


    private static long id;
    private static String username;
    private static String password;


    public User(long id, String username, String password) {
        User.id = id;
        User.username = username;
        User.password = password;
    }

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
        User.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }
}
