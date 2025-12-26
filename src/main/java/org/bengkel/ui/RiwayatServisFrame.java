package org.bengkel.ui;

import org.bengkel.model.Transaksi;
import org.bengkel.util.DataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;

public class RiwayatServisFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public RiwayatServisFrame() {
        setTitle("Riwayat Servis");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color merah = new Color(137, 0, 37);
        Color bg = new Color(245, 247, 250);

        // ================= HEADER =================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(merah);
        header.setPreferredSize(new Dimension(0, 50));
        header.setBorder(new EmptyBorder(0, 15, 0, 15));

        JLabel lblTitle = new JLabel("RIWAYAT SERVIS");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.add(lblTitle, BorderLayout.WEST);

        add(header, BorderLayout.NORTH);

        // ================= CONTENT =================
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(bg);
        content.setBorder(new EmptyBorder(15, 15, 15, 15));

        model = new DefaultTableModel(
                new Object[]{"ID", "Tanggal", "Suku Cadang", "Jumlah", "Total"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(25);
        table.setSelectionBackground(new Color(255, 220, 220));

        loadData();

        content.add(new JScrollPane(table), BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);

        // ================= FOOTER =================
        JPanel footer = new JPanel();
        footer.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton btnDetail = new JButton("Lihat Struk");
        btnDetail.setBackground(merah);
        btnDetail.setForeground(Color.WHITE);
        btnDetail.setFocusPainted(false);
        btnDetail.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnDetail.addActionListener(e -> lihatStruk());

        btnDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnDetail.setBackground(merah.darker());
            }

            public void mouseExited(MouseEvent evt) {
                btnDetail.setBackground(merah);
            }
        });

        footer.add(btnDetail);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadData() {
        DataManager.loadTransaksi();
        model.setRowCount(0);

        for (Transaksi t : DataManager.transaksiList) {
            model.addRow(new Object[]{
                    t.getId(),
                    t.getTanggal(),
                    t.getNamaSC(),
                    t.getJumlah(),
                    t.getTotal()
            });
        }
    }

    private void lihatStruk() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih transaksi terlebih dahulu");
            return;
        }

        Transaksi t = DataManager.transaksiList.get(row);
        new StrukFrame(t);
    }
}
