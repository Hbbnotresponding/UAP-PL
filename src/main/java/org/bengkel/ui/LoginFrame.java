package org.bengkel.ui;

import org.bengkel.model.User;
import org.bengkel.util.DataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;

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
        Color orange = new Color(255,140,0);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(merah);
        mainPanel.setLayout(new BorderLayout());

        // ===== LOGO =====
        ImageIcon logoIcon = new ImageIcon(
                getClass().getResource("/images/logo.png")
        );

        Image img = logoIcon.getImage();

        int targetHeight = 80;
        int targetWidth = (img.getWidth(null) * targetHeight) / img.getHeight(null);

        Image scaled = img.getScaledInstance(
                targetWidth,
                targetHeight,
                Image.SCALE_SMOOTH
        );

        JLabel lblLogo = new JLabel(new ImageIcon(scaled));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);

        //==== TITLE =====
        JLabel lblTitle = new JLabel(
                "<html><center>BENGKEL<br>SANG SURYA MOTOR</center></html>",
                SwingConstants.CENTER
        );
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(putih);

        //====== PANEL LOGO=====
        JPanel logoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
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

        //===== BUTTON =====
        JButton btnLogin = new JButton("Masuk");
        btnLogin.setBackground(orange);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);

        btnLogin.addActionListener(e -> login());

        btnLogin.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                btnLogin.setBackground(orange.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnLogin.setBackground(Color.orange);
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
