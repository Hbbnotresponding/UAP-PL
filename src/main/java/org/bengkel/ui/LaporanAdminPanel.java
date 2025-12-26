package org.bengkel.ui;

import org.bengkel.model.Transaksi;
import org.bengkel.util.DataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LaporanAdminPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    private JLabel lblTotalTransaksi;
    private JLabel lblTotalPendapatan;
    private JLabel lblTotalServis;

    public LaporanAdminPanel() {

        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(20, 25, 20, 25));
        setBackground(new Color(236, 240, 245));

        // ================= JUDUL =================
        JLabel title = new JLabel("Laporan Transaksi");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        // ================= FILTER =================
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        filterPanel.setBackground(Color.WHITE);
        filterPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JTextField txtFrom = new JTextField(10);
        JTextField txtTo = new JTextField(10);

        JButton btnFilter = new JButton("Tampilkan");

        filterPanel.add(new JLabel("Dari Tanggal:"));
        filterPanel.add(txtFrom);
        filterPanel.add(new JLabel("Sampai Tanggal:"));
        filterPanel.add(txtTo);
        filterPanel.add(btnFilter);

        // ================= RINGKASAN =================
        JPanel summaryPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        summaryPanel.setOpaque(false);

        lblTotalTransaksi = new JLabel("0");
        lblTotalPendapatan = new JLabel("Rp 0");
        lblTotalServis = new JLabel("0");

        summaryPanel.add(createSummaryBox("Total Transaksi", lblTotalTransaksi));
        summaryPanel.add(createSummaryBox("Total Pendapatan", lblTotalPendapatan));
        summaryPanel.add(createSummaryBox("Total Servis", lblTotalServis));

        JPanel topPanel = new JPanel(new BorderLayout(0, 15));
        topPanel.setOpaque(false);
        topPanel.add(filterPanel, BorderLayout.NORTH);
        topPanel.add(summaryPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.CENTER);

        // ================= TABEL =================
        String[] columnNames = {
                "Tanggal",
                "No Transaksi",
                "Kasir",
                "Jenis Servis",
                "Total Bayar"
        };

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);

        // ================= BUTTON =================
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnPrint = new JButton("Cetak Laporan");
        JButton btnExport = new JButton("Export Excel");

        bottomPanel.add(btnExport);
        bottomPanel.add(btnPrint);

        JPanel tablePanel = new JPanel(new BorderLayout(10, 10));
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(bottomPanel, BorderLayout.SOUTH);

        add(tablePanel, BorderLayout.SOUTH);

        // ================= LOAD DATA =================
        loadData();
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

    // ================= LOAD DATA =================
    private void loadData() {

        model.setRowCount(0);
        DataManager.loadTransaksi();

        int totalPendapatan = 0;
        int totalTransaksi = DataManager.transaksiList.size();

        for (Transaksi t : DataManager.transaksiList) {

            model.addRow(new Object[]{
                    t.getTanggal().toLocalDate(),
                    t.getId(),
                    "-",
                    t.getNamaSC(),
                    "Rp " + t.getTotal()
            });

            totalPendapatan += t.getTotal();
        }

        lblTotalTransaksi.setText(String.valueOf(totalTransaksi));
        lblTotalPendapatan.setText("Rp " + totalPendapatan);
        lblTotalServis.setText(String.valueOf(totalTransaksi));
    }
}
