package bengkel.ui;

import org.bengkel.model.User;
import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard(User user) {
        setTitle("Admin Dashboard");
        setSize(500,300);
        setLocationRelativeTo(null);

        JButton btnSuku = new JButton("Kelola Suku Cadang");
        JButton btnTrans = new JButton("Input Transaksi");

        setLayout(new GridLayout(2,1,15,15));
        add(btnSuku);
        add(btnTrans);

        setVisible(true);
    }
}
