package org.bengkel.ui;

import org.bengkel.model.Transaksi;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StrukFrame extends JFrame {

    public StrukFrame(Transaksi t) {
        setTitle("Struk Pembayaran");
        setSize(400, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== WARNA TEMA =====
        Color tosca = new Color(0, 137, 132);
        Color bg = new Color(245, 247, 250);

        // ================= HEADER =================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(tosca);
        header.setPreferredSize(new Dimension(0, 50));
        header.setBorder(new EmptyBorder(0, 15, 0, 15));

        JLabel lblTitle = new JLabel("STRUK PEMBAYARAN");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));

        header.add(lblTitle, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        // ================= CONTENT =================
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(bg);
        content.setBorder(new EmptyBorder(15, 15, 15, 15));

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Consolas", Font.PLAIN, 12));
        area.setBackground(Color.WHITE);
        area.setBorder(new EmptyBorder(15, 15, 15, 15));

        area.setText(
                "BENGKEL SANG SURYA\n" +
                        "===========================\n" +
                        "ID Transaksi : " + t.getId() + "\n" +
                        "Tanggal      : " + t.getTanggal() + "\n" +
                        "Nama         : " + t.getNamaSC() + "\n" +
                        "Plat         : "  + "\n" +
                        "--------------------------------\n" +
                        "Servis       : "  + "\n" +
                        "Suku Cadang  : " + t.getNamaSC() + "\n" +
                        "Jumlah       : " + t.getJumlah() + "\n" +
                        "--------------------------------\n" +
                        "TOTAL BAYAR  : Rp " + t.getTotal() + "\n" +
                        "===========================\n\n" +
                        "Terima kasih atas kepercayaan Anda"
        );

        content.add(new JScrollPane(area), BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);

        // ================= FOOTER =================
        JPanel footer = new JPanel();
        footer.setBackground(bg);

        JButton btnTutup = new JButton("Tutup");
        btnTutup.setFocusPainted(false);
        btnTutup.setBackground(tosca);
        btnTutup.setForeground(Color.WHITE);
        btnTutup.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnTutup.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTutup.addActionListener(e -> dispose());

        footer.add(btnTutup);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }
}
