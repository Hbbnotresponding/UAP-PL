package org.bengkel.ui;

import org.bengkel.model.Transaksi;
import org.bengkel.util.DataManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class FormRiwayatTransaksi extends JPanel {

    JTable table;
    DefaultTableModel model;

    Color BG = new Color(240, 243, 246);
    Color CARD = Color.WHITE;

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    public FormRiwayatTransaksi() {

        setLayout(new BorderLayout());
        setBackground(BG);

        // ================= HEADER =================
        JLabel title = new JLabel("RIWAYAT TRANSAKSI");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 25, 10, 25));

        add(title, BorderLayout.NORTH);

        // ================= TABLE =================
        model = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Tanggal",
                        "Kode SC",
                        "Nama Suku Cadang",
                        "Jumlah",
                        "Total"
                }, 0
        ) {
            public boolean isCellEditable(int r, int c) {
                return false; // READ ONLY
            }
        };

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.setSelectionBackground(new Color(220, 235, 250));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // ================= CARD =================
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(CARD);
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        card.add(scrollPane, BorderLayout.CENTER);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(BG);
        center.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        center.add(card, BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);

        // ================= LOAD DATA ASLI =================
        DataManager.loadTransaksi();
        refreshTable();
    }

    void refreshTable() {
        model.setRowCount(0);
        for (Transaksi t : DataManager.transaksiList) {
            model.addRow(new Object[]{
                    t.getId(),
                    t.getTanggal().format(formatter),
                    t.getKodeSC(),
                    t.getNamaSC(),
                    t.getJumlah(),
                    "Rp " + String.format("%,d", t.getTotal())
            });
        }
    }
}
