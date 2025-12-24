package org.bengkel.ui;

import org.bengkel.model.*;
import org.bengkel.util.DataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;

public class FormTransaksi extends JFrame {

    private JComboBox<String> cmbSC;
    private JTextField txtJumlah;
    private JLabel lblTotal;
    private int total = 0;

    public FormTransaksi() {
        setTitle("Transaksi Servis");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color tosca = new Color(0, 137, 132);
        Color bg = new Color(245, 247, 250);

        // ================= HEADER =================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(tosca);
        header.setPreferredSize(new Dimension(0, 50));
        header.setBorder(new EmptyBorder(0, 15, 0, 15));

        JLabel lblTitle = new JLabel("FORM TRANSAKSI");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.add(lblTitle, BorderLayout.WEST);

        add(header, BorderLayout.NORTH);

        // ================= CONTENT =================
        JPanel content = new JPanel(new GridLayout(5, 1, 10, 10));
        content.setBackground(bg);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));

        DataManager.loadSukuCadang();

        cmbSC = new JComboBox<>();
        for (SukuCadang sc : DataManager.sukuCadangList) {
            cmbSC.addItem(sc.getKode() + " - " + sc.getNama());
        }

        txtJumlah = new JTextField();
        lblTotal = new JLabel("Total: Rp 0");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));

        content.add(new JLabel("Suku Cadang"));
        content.add(cmbSC);
        content.add(new JLabel("Jumlah"));
        content.add(txtJumlah);
        content.add(lblTotal);

        add(content, BorderLayout.CENTER);

        // ================= BUTTON =================
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bg);

        JButton btnHitung = new JButton("Hitung");
        JButton btnSimpan = new JButton("Simpan & Cetak Struk");

        styleButton(btnHitung, new Color(52, 152, 219));
        styleButton(btnSimpan, tosca);

        btnHitung.addActionListener(e -> hitung());
        btnSimpan.addActionListener(e -> simpan());

        buttonPanel.add(btnHitung);
        buttonPanel.add(btnSimpan);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ================= LOGIKA =================
    private void hitung() {
        try {
            int idx = cmbSC.getSelectedIndex();
            int jumlah = Integer.parseInt(txtJumlah.getText());

            SukuCadang sc = DataManager.sukuCadangList.get(idx);
            total = sc.getHarga() * jumlah;

            lblTotal.setText("Total: Rp " + total);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void simpan() {
        try {
            if (total == 0){
                JOptionPane.showMessageDialog(this,
                        "Silahkan Hitung Total Terlebih Dahulu!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
            }

            int idx = cmbSC.getSelectedIndex();
            int jumlah = Integer.parseInt(txtJumlah.getText());
            SukuCadang sc = DataManager.sukuCadangList.get(idx);

            Transaksi t = new Transaksi(
                    "T" + (DataManager.transaksiList.size() + 1),
                    LocalDateTime.now(),
                    sc.getKode(),
                    sc.getNama(),
                    jumlah,
                    total
            );

            DataManager.tambahTransaksi(t);
            DataManager.saveTransaksi();

            new StrukFrame(t);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data Transaksi Tidak Valid",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // ================= STYLE BUTTON =================
    private void styleButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
