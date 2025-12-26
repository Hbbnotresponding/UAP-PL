package org.bengkel.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataKasirPanel extends JPanel {

    JTable table;
    DefaultTableModel model;
    JTextField txtNama, txtUsername;

    public DataKasirPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(236, 240, 245));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // ===== TITLE =====
        JLabel title = new JLabel("Data Kasir");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        // ===== TABLE =====
        String[] col = {"ID", "Nama Kasir", "Username"};
        model = new DefaultTableModel(col, 0);
        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // ===== FORM INPUT =====
        JPanel form = new JPanel(new GridLayout(1, 5, 10, 10));
        form.setBorder(new EmptyBorder(15, 0, 0, 0));

        txtNama = new JTextField();
        txtUsername = new JTextField();

        JButton btnTambah = new JButton("Tambah");
        JButton btnHapus = new JButton("Hapus");

        btnTambah.addActionListener(e -> tambah());
        btnHapus.addActionListener(e -> hapus());

        form.add(new JLabel("Nama"));
        form.add(txtNama);
        form.add(new JLabel("Username"));
        form.add(txtUsername);
        form.add(btnTambah);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(form, BorderLayout.CENTER);
        bottom.add(btnHapus, BorderLayout.EAST);

        add(bottom, BorderLayout.SOUTH);

        // DATA CONTOH (sementara)
        loadDummy();
    }

    void tambah() {
        if (txtNama.getText().isEmpty() || txtUsername.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lengkapi data!");
            return;
        }

        model.addRow(new Object[]{
                model.getRowCount() + 1,
                txtNama.getText(),
                txtUsername.getText()
        });

        txtNama.setText("");
        txtUsername.setText("");
    }

    void hapus() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            model.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data dulu!");
        }
    }

    void loadDummy() {
        model.addRow(new Object[]{1, "Budi", "budi01"});
        model.addRow(new Object[]{2, "Ani", "ani02"});
    }
}


