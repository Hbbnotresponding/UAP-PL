package bengkel.ui;

import org.bengkel.model.User;
import javax.swing.*;
import java.awt.*;

public class UserDashboard extends JFrame {

    public UserDashboard(User user) {
        setTitle("User Dashboard");
        setSize(400,250);
        setLocationRelativeTo(null);

        JButton btnRiwayat = new JButton("Riwayat Servis");
        JButton btnStruk = new JButton("Lihat Struk");

        setLayout(new GridLayout(2,1,10,10));
        add(btnRiwayat);
        add(btnStruk);

        btnStruk.addActionListener(e -> new StrukFrame());

        setVisible(true);
    }
}
