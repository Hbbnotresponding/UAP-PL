package org.bengkel.ui;

import org.bengkel.model.Transaksi;

import javax.swing.*;
import java.awt.*;

public class StrukFrame extends JFrame {

    public StrukFrame(Transaksi t) {
        setTitle("Struk Pembayaran");
        setSize(350, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));

        area.setText(
                "=== BENGKEL SANG SURYA ===\n\n" +
                        "STRUK SERVIS BENGKEL\n" +
                        "----------------------------\n" +
                        "ID Transaksi : " + t.getId() + "\n" +
                        "Tanggal      : " + t.getTanggal() + "\n" +
                        "----------------------------\n" +
                        "Suku Cadang  : " + t.getNamaSC() + "\n" +
                        "Jumlah       : " + t.getJumlah() + "\n" +
                        "----------------------------\n" +
                        "TOTAL BAYAR  : Rp " + t.getTotal() + "\n\n" +
                        "Terima Kasih\n"
        );

        add(new JScrollPane(area), BorderLayout.CENTER);
        setVisible(true);
    }
}
