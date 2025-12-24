package org.bengkel.ui;

import org.bengkel.model.Transaksi;
import org.bengkel.util.DataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RiwayatTransaksiAdminFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public RiwayatTransaksiAdminFrame() {
        setTitle("Riwayat Transaksi - Admin");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        DataManager.loadTransaksi();

        String[] col = {
                "ID", "Tanggal", "Kode SC",
                "Nama SC", "Jumlah", "Total"
        };

        model = new DefaultTableModel(col, 0);
        table = new JTable(model);

        loadData();

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(scroll, BorderLayout.CENTER);
        setVisible(true);
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
