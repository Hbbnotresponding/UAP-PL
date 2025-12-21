package org.bengkel.ui;

import javax.swing.*;

public class StrukFrame extends JFrame {

    public StrukFrame() {
        setTitle("Struk Pembayaran");
        setSize(350,300);
        setLocationRelativeTo(null);

        JTextArea area = new JTextArea();
        area.setEditable(false);

        area.setText(
                "STRUK SERVIS BENGKEL\n" +
                        "--------------------\n" +
                        "Nama    : Andi\n" +
                        "Plat    : N 1234 AB\n" +
                        "Servis  : Ganti Oli\n" +
                        "Total   : Rp 150.000\n" +
                        "--------------------\n" +
                        "Terima Kasih\n"
        );

        add(new JScrollPane(area));
        setVisible(true);
    }
}
