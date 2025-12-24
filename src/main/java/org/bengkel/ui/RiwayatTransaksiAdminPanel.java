package org.bengkel.ui;

import org.bengkel.model.Transaksi;
import org.bengkel.util.DataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RiwayatTransaksiAdminPanel extends JPanel {

    JTable table;
    DefaultTableModel model;

    public RiwayatTransaksiAdminPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(236, 240, 245));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Riwayat Transaksi");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        DataManager.loadTransaksi();

        String[] col = {
                "ID", "Tanggal", "Kode SC",
                "Nama SC", "Jumlah", "Total"
        };

        model = new DefaultTableModel(col, 0);
        table = new JTable(model);
        loadData();

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);
    }

    void loadData() {
        model.setRowCount(0);
        for (Transaksi t : DataManager.transaksiList) {
            model.addRow(new Object[]{
                    t.getId(),
                    t.getTanggal(),
                    t.getKodeSC(),
                    t.getNamaSC(),
                    t.getJumlah(),
                    t.getTotal()
            });
        }
    }
}
