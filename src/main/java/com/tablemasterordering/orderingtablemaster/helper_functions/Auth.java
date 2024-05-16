package com.tablemasterordering.orderingtablemaster.helper_functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Auth {
    public static String username;

    public static boolean checkSignedInStatus() {
        String usernameFromCookie = "";

        try {
            usernameFromCookie = readCookie();
        } catch (IOException e) {
            System.out.println("Error reading cookie from file");
            throw new RuntimeException(e);
        }

        if (usernameFromCookie == null || usernameFromCookie.isEmpty()) {
            return false;
        }

        username = usernameFromCookie;

        return true;
    }

    private static String readCookie() throws IOException {
        BufferedReader reader = null;
        String data = "";

        try{
            reader = new BufferedReader(new FileReader("cookie.txt"));
            data = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return data;
    }
}
