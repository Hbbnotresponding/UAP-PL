package org.bengkel.ui;

import org.bengkel.model.User;
import org.bengkel.util.DataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    JTextField txtUsername;
    JPasswordField txtPassword;
    JComboBox<String> cmbRole;

    public LoginFrame() {
        setTitle("Login | Bengkel Anugrah Motor");
        setSize(450, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color tosca = new Color(0, 137, 132);
        Color kuning = new Color(255, 171, 0);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(tosca);
        mainPanel.setLayout(new BorderLayout());

        // ===== LOGO =====
        JLabel lblLogo = new JLabel("🔧", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 80));
        lblLogo.setForeground(Color.WHITE);

        JLabel lblTitle = new JLabel("BENGKEL\nANUGRAH MOTOR", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(kuning);

        JPanel logoPanel = new JPanel(new GridLayout(2,1));
        logoPanel.setOpaque(false);
        logoPanel.add(lblLogo);
        logoPanel.add(lblTitle);

        mainPanel.add(logoPanel, BorderLayout.NORTH);

        // ===== FORM =====
        JPanel formPanel = new JPanel();
        formPanel.setBackground(kuning);
        formPanel.setBorder(new EmptyBorder(30,30,30,30));
        formPanel.setLayout(new GridLayout(5,1,10,10));

        txtUsername = new JTextField();
        txtUsername.setBorder(BorderFactory.createTitledBorder("Username"));

        txtPassword = new JPasswordField();
        txtPassword.setBorder(BorderFactory.createTitledBorder("Password"));

        cmbRole = new JComboBox<>(new String[]{
                "==== Login Sebagai ====",
                "ADMIN",
                "USER"
        });

        JButton btnLogin = new JButton("Masuk");
        btnLogin.setBackground(tosca);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);

        btnLogin.addActionListener(e -> login());

        formPanel.add(txtUsername);
        formPanel.add(txtPassword);
        formPanel.add(cmbRole);
        formPanel.add(new JLabel());
        formPanel.add(btnLogin);

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        center.add(formPanel);

        mainPanel.add(center, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private void login() {
        if (cmbRole.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Pilih role login!");
            return;
        }

        User user = DataManager.login(
                txtUsername.getText(),
                new String(txtPassword.getPassword())
        );

        if (user != null && user.getRole().equalsIgnoreCase(cmbRole.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Login berhasil");

            if (user.getRole().equalsIgnoreCase("ADMIN")) {
                new AdminDashboard();
            } else {
                new UserDashboard();
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Login gagal");
        }
    }
}
