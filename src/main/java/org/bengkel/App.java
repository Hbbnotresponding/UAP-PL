package org.bengkel;

import org.bengkel.ui.LoginFrame;
import org.bengkel.util.DataManager;

public class App {
    public static void main(String[] args) {
        DataManager.loadUser();
        new LoginFrame();
    }
}
