package bengkel.ui;

import org.bengkel.model.User;
import org.bengkel.util.DataManager;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Bengkel App - Login");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(30,30,60));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel lblTitle = new JLabel("SISTEM BENGKEL");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JTextField txtUser = new JTextField(15);
        JPasswordField txtPass = new JPasswordField(15);
        JButton btnLogin = new JButton("LOGIN");

        btnLogin.setBackground(new Color(70,130,180));
        btnLogin.setForeground(Color.WHITE);

        gbc.gridy=0; panel.add(lblTitle,gbc);
        gbc.gridy=1; panel.add(txtUser,gbc);
        gbc.gridy=2; panel.add(txtPass,gbc);
        gbc.gridy=3; panel.add(btnLogin,gbc);

        btnLogin.addActionListener(e -> {
            User user = DataManager.login(
                    txtUser.getText(),
                    new String(txtPass.getPassword())
            );

            if (user != null) {
                dispose();
                if (user.getRole().equals("ADMIN"))
                    new AdminDashboard(user);
                else
                    new UserDashboard(user);
            } else {
                JOptionPane.showMessageDialog(this,"Login gagal");
            }
        });

        add(panel);
        setVisible(true);
    }
}
