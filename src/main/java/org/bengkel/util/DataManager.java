package org.bengkel.util;

import bengkel.model.User;
import java.io.*;
import java.util.*;

public class DataManager {

    public static List<User> users = new ArrayList<>();

    public static void loadUsers() {
        users.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("data/user.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                users.add(new User(d[0], d[1], d[2]));
            }
        } catch (IOException e) {
            System.out.println("File user belum ada.");
        }
    }

    public static User login(String u, String p) {
        for (User user : users) {
            if (user.getUsername().equals(u) && user.getPassword().equals(p)) {
                return user;
            }
        }
        return null;
    }
}
