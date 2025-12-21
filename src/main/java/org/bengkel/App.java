package org.bengkel;

import bengkel.ui.LoginFrame;
import org.bengkel.util.DataManager;

public class App {
    public static void main(String[] args) {
        DataManager.loadUsers();
        new LoginFrame();
    }
}
