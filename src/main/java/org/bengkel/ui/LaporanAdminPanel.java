package org.bengkel.ui;

import org.bengkel.model.Transaksi;
import org.bengkel.util.DataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;

public class LaporanAdminPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JLabel lblTotalTrx, lblTotalPendapatan;

    public LaporanAdminPanel() {
        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(20, 25, 20, 25));
        setBackground(new Color(236, 240, 245));

        DataManager.loadTransaksi();

        // ================= JUDUL =================
        JLabel title = new JLabel("Laporan Transaksi");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        // ================= SUMMARY =================
        JPanel summaryPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        summaryPanel.setOpaque(false);

        lblTotalTrx = new JLabel("0");
        lblTotalPendapatan = new JLabel("Rp 0");

        summaryPanel.add(createSummaryBox("Total Transaksi", lblTotalTrx));
        summaryPanel.add(createSummaryBox("Total Pendapatan", lblTotalPendapatan));

        add(summaryPanel, BorderLayout.CENTER);

        // ================= TABLE =================
        String[] columnNames = {
                "Tanggal",
                "ID Transaksi",
                "Kode SC",
                "Nama Suku Cadang",
                "Jumlah",
                "Total Bayar"
        };

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setRowHeight(25);

        loadTable();

        JScrollPane scrollPane = new JScrollPane(table);

        // ================= BUTTON =================
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnExport = new JButton("Export Excel (CSV)");
        btnExport.addActionListener(e -> exportCSV());

        bottomPanel.add(btnExport);

        JPanel tablePanel = new JPanel(new BorderLayout(10, 10));
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(bottomPanel, BorderLayout.SOUTH);

        add(tablePanel, BorderLayout.SOUTH);
    }

    // ================= LOAD DATA =================
    private void loadTable() {
        model.setRowCount(0);

        int totalTrx = 0;
        int totalPendapatan = 0;

        for (Transaksi t : DataManager.transaksiList) {
            model.addRow(new Object[]{
                    t.getTanggal().format(
                            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
                    ),
                    t.getId(),
                    t.getKodeSC(),
                    t.getNamaSC(),
                    t.getJumlah(),
                    "Rp " + t.getTotal()
            });

            totalTrx++;
            totalPendapatan += t.getTotal();
        }

        lblTotalTrx.setText(String.valueOf(totalTrx));
        lblTotalPendapatan.setText("Rp " + totalPendapatan);
    }

    // ================= SUMMARY BOX =================
    private JPanel createSummaryBox(String title, JLabel valueLabel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(52, 152, 219));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setForeground(Color.WHITE);

        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));

        panel.add(lblTitle, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }

    // ================= EXPORT CSV =================
    private void exportCSV() {
        try {
            FileWriter fw = new FileWriter("laporan_transaksi.csv");
            fw.write("Tanggal,ID,Kode SC,Nama SC,Jumlah,Total\n");

            for (Transaksi t : DataManager.transaksiList) {
                fw.write(
                        t.getTanggal() + "," +
                                t.getId() + "," +
                                t.getKodeSC() + "," +
                                t.getNamaSC() + "," +
                                t.getJumlah() + "," +
                                t.getTotal() + "\n"
                );
            }

            fw.close();
            JOptionPane.showMessageDialog(this,
                    "Berhasil export laporan_transaksi.csv");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal export file");
        }
    }
}
