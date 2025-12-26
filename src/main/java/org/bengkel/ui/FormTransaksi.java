package org.bengkel.ui;

import org.bengkel.model.SukuCadang;
import org.bengkel.model.Transaksi;
import org.bengkel.util.DataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

public class FormTransaksi extends JPanel {

    private JComboBox<String> cmbSC;
    private JTextField txtJumlah;
    private JLabel lblTotal;
    private int total = 0;

    public FormTransaksi() {
        setLayout(new BorderLayout());

        Color merah = new Color(137, 0, 37);
        Color bg = new Color(245, 247, 250);
        Color orange = new Color(243, 156, 18);

        // ================= HEADER =================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(orange);
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

        styleButton(btnHitung, orange);
        styleButton(btnSimpan, merah);

        btnHitung.addActionListener(e -> hitung());
        btnSimpan.addActionListener(e -> simpan());

        btnHitung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnHitung.setBackground(orange.darker());
                btnSimpan.setBackground(merah.darker());
            }

            public void mouseExited(MouseEvent evt) {
                btnHitung.setBackground(orange);
                btnSimpan.setBackground(merah);
            }
        });

        buttonPanel.add(btnHitung);
        buttonPanel.add(btnSimpan);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // ================= LOGIKA =================
    private void hitung() {
        try {
            if (cmbSC.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this,
                        "Data suku cadang belum tersedia!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int jumlah = Integer.parseInt(txtJumlah.getText());
            if (jumlah <= 0) throw new NumberFormatException();

            SukuCadang sc = DataManager.sukuCadangList.get(cmbSC.getSelectedIndex());
            total = sc.getHarga() * jumlah;
            lblTotal.setText("Total: Rp " + total);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Jumlah harus berupa angka lebih dari 0!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void simpan() {
        try {
            if (total == 0) {
                JOptionPane.showMessageDialog(this,
                        "Hitung total terlebih dahulu!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int jumlah = Integer.parseInt(txtJumlah.getText());
            SukuCadang sc = DataManager.sukuCadangList.get(cmbSC.getSelectedIndex());

            Transaksi t = new Transaksi(
                    "T" + System.currentTimeMillis(),
                    LocalDateTime.now(),
                    sc.getKode(),
                    sc.getNama(),
                    jumlah,
                    total
            );

            DataManager.tambahTransaksi(t);
            DataManager.saveTransaksi();

            // Struk boleh JFrame (popup)
            new StrukFrame(t);

            JOptionPane.showMessageDialog(this,
                    "Transaksi berhasil disimpan!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal menyimpan transaksi!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // ================= STYLE =================
    private void styleButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
