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

        Color merah = new Color(137, 0, 37);
        Color putih = new Color(255, 255, 255);
        Color orange = new Color(255, 140, 0);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(merah);
        mainPanel.setLayout(new BorderLayout());

        // ===== LOGO =====
        ImageIcon logoIcon = new ImageIcon(
                getClass().getResource("/image/logo_bengkel.png")
        );

        Image img = logoIcon.getImage();

        int targetHeight = 80;
        int targetWidth = (img.getWidth(null) * targetHeight) / img.getHeight(null);

        Image scaled = img.getScaledInstance(
                targetWidth,
                targetHeight,
                Image.SCALE_SMOOTH
        );

        // ===== JLABEL LOGO ========
        JLabel lblLogo = new JLabel(new ImageIcon(scaled));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);

        //===== Title =======
        JLabel lblTitle = new JLabel(
                "<html><center>BENGKEL<br>ANUGRAH MOTOR</center></html>",
                SwingConstants.CENTER
        );

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(putih);

        // ====== Panel logo =======
        JPanel logoPanel = new JPanel(new GridLayout(2,1, 5, 5));
        logoPanel.setOpaque(false);
        logoPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        logoPanel.add(lblLogo);
        logoPanel.add(lblTitle);

        mainPanel.add(logoPanel, BorderLayout.NORTH);

        // ===== FORM =====
        JPanel formPanel = new JPanel();
        formPanel.setBackground(putih);
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

        // ========== buton =========
        JButton btnLogin = new JButton("Masuk");
        btnLogin.setBackground(orange);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);

        btnLogin.addActionListener(e -> login());

        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(merah.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(Color.WHITE);
            }
        });


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
                new AdminDashboard(user);
            } else {
                new UserDashboard();
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Login gagal");
        }
    }
}



