package org.bengkel.ui;

import org.bengkel.model.SukuCadang;
import org.bengkel.util.DataManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FormSukuCadang extends JPanel {

    JTextField txtKode, txtNama, txtHarga;
    DefaultTableModel model;
    JTable table;

    JButton btnTambah, btnEdit, btnHapus;

    Color ORANGE = new Color(245, 157, 0);
    Color BLUE = new Color(52, 120, 180);
    Color BG = new Color(240, 243, 246);

    public FormSukuCadang() {

        setLayout(new BorderLayout());
        setBackground(BG);

        // ================= HEADER =================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ORANGE);
        header.setPreferredSize(new Dimension(100, 60));

        JLabel title = new JLabel("DATA SUKU CADANG");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        // ================= FORM CARD =================
        JPanel formCard = new JPanel(new GridBagLayout());
        formCard.setBackground(Color.WHITE);
        formCard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtKode = new JTextField(15);
        txtNama = new JTextField(15);
        txtHarga = new JTextField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        formCard.add(new JLabel("Kode Suku Cadang"), gbc);
        gbc.gridx = 1;
        formCard.add(txtKode, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formCard.add(new JLabel("Nama Suku Cadang"), gbc);
        gbc.gridx = 1;
        formCard.add(txtNama, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formCard.add(new JLabel("Harga"), gbc);
        gbc.gridx = 1;
        formCard.add(txtHarga, gbc);

        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");

        styleButton(btnTambah, ORANGE);
        styleButton(btnEdit, BLUE);
        styleButton(btnHapus, Color.RED);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(btnTambah);
        btnPanel.add(btnEdit);
        btnPanel.add(btnHapus);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        formCard.add(btnPanel, gbc);
        gbc.gridwidth = 1;

        // ================= TABLE =================
        model = new DefaultTableModel(new String[]{"Kode", "Nama", "Harga"}, 0);
        table = new JTable(model);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel tableCard = new JPanel(new BorderLayout());
        tableCard.setBackground(Color.WHITE);
        tableCard.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        tableCard.add(scrollPane, BorderLayout.CENTER);

        // ================= CENTER =================
        JPanel center = new JPanel(new BorderLayout(15, 15));
        center.setBackground(BG);
        center.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        center.add(formCard, BorderLayout.NORTH);
        center.add(tableCard, BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);

        // ================= DATA =================
        DataManager.loadSukuCadang();
        refreshTable();

        // ================= BUTTON STATE =================
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                boolean selected = table.getSelectedRow() >= 0;
                btnEdit.setEnabled(selected);
                btnHapus.setEnabled(selected);

                if (selected) {
                    int row = table.getSelectedRow();
                    txtKode.setText(model.getValueAt(row, 0).toString());
                    txtNama.setText(model.getValueAt(row, 1).toString());
                    txtHarga.setText(model.getValueAt(row, 2).toString());
                }
            }
        });

        // ================= ACTION =================
        btnTambah.addActionListener(e -> tambah());
        btnEdit.addActionListener(e -> edit());
        btnHapus.addActionListener(e -> hapus());
    }

    // ================= METHOD =================

    void refreshTable() {
        model.setRowCount(0);
        for (SukuCadang sc : DataManager.sukuCadangList) {
            model.addRow(new Object[]{
                    sc.getKode(), sc.getNama(), sc.getHarga()
            });
        }
    }

    void tambah() {
        try {
            DataManager.sukuCadangList.add(
                    new SukuCadang(
                            txtKode.getText(),
                            txtNama.getText(),
                            Integer.parseInt(txtHarga.getText())
                    )
            );
            DataManager.saveSukuCadang();
            refreshTable();
            clearForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka!");
        }
    }

    void edit() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            try {
                SukuCadang sc = DataManager.sukuCadangList.get(row);
                sc.setNama(txtNama.getText());
                sc.setHarga(Integer.parseInt(txtHarga.getText()));
                DataManager.saveSukuCadang();
                refreshTable();
                clearForm();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Harga harus berupa angka!");
            }
        }
    }

    void hapus() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            if (JOptionPane.showConfirmDialog(
                    this,
                    "Yakin ingin menghapus data ini?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION
            ) == JOptionPane.YES_OPTION) {

                DataManager.sukuCadangList.remove(row);
                DataManager.saveSukuCadang();
                refreshTable();
                clearForm();
            }
        }
    }

    void clearForm() {
        txtKode.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        table.clearSelection();
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
    }

    void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
    }
}
