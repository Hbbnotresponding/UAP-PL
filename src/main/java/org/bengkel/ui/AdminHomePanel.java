package org.bengkel.ui;

import org.bengkel.model.Transaksi;
import org.bengkel.util.DataManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;

public class AdminHomePanel extends JPanel {

    private JLabel lblTotalTransaksi;
    private JLabel lblPendapatan;
    private GrafikTransaksiPanel grafikPanel;

    public AdminHomePanel() {
        DataManager.loadTransaksi();

        setLayout(new BorderLayout());
        setBackground(new Color(236, 240, 245));
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel titleHome = new JLabel("Beranda");
        titleHome.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        add(titleHome, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        statsPanel.setOpaque(false);
        statsPanel.setPreferredSize(new Dimension(0, 150));

        lblTotalTransaksi = new JLabel();
        lblPendapatan = new JLabel();

        statsPanel.add(createStatBox(
                lblTotalTransaksi,
                "Transaksi hari ini",
                "✔",
                new Color(243, 156, 18)));

        statsPanel.add(createStatBox(
                lblPendapatan,
                "Pendapatan hari ini",
                "+",
                new Color(46, 204, 113)));

        grafikPanel = new GrafikTransaksiPanel();

        JPanel graphContainer = new JPanel(new BorderLayout());
        graphContainer.setBackground(Color.WHITE);
        graphContainer.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        JLabel graphTitle = new JLabel("GRAFIK TRANSAKSI", JLabel.CENTER);
        graphTitle.setBorder(new EmptyBorder(15, 0, 15, 0));
        graphTitle.setFont(new Font("Arial", Font.BOLD, 14));

        graphContainer.add(graphTitle, BorderLayout.NORTH);
        graphContainer.add(grafikPanel, BorderLayout.CENTER);

        JPanel centerBody = new JPanel(new BorderLayout(0, 30));
        centerBody.setOpaque(false);
        centerBody.add(statsPanel, BorderLayout.NORTH);
        centerBody.add(graphContainer, BorderLayout.CENTER);

        add(centerBody, BorderLayout.CENTER);

        refreshData(); // ← pertama kali
    }

    // ================= REFRESH =================
    public void refreshData() {
        DataManager.loadTransaksi();

        lblTotalTransaksi.setText(
                String.valueOf(getTotalTransaksiHariIni())
        );

        lblPendapatan.setText(
                "Rp " + getPendapatanHariIni()
        );

        grafikPanel.refresh();
    }

    private int getTotalTransaksiHariIni() {
        return (int) DataManager.transaksiList.stream()
                .filter(t -> t.getTanggal().toLocalDate().equals(java.time.LocalDate.now()))
                .count();
    }

    private int getPendapatanHariIni() {
        return DataManager.transaksiList.stream()
                .filter(t -> t.getTanggal().toLocalDate().equals(java.time.LocalDate.now()))
                .mapToInt(org.bengkel.model.Transaksi::getTotal)
                .sum();
    }

    private JPanel createStatBox(JLabel lblVal, String desc, String icon, Color col) {
        JPanel box = new JPanel(new BorderLayout());
        box.setBackground(col);

        lblVal.setFont(new Font("Arial", Font.BOLD, 35));
        lblVal.setForeground(Color.WHITE);
        lblVal.setHorizontalAlignment(JLabel.RIGHT);

        JLabel lblDesc = new JLabel(desc, JLabel.RIGHT);
        lblDesc.setForeground(Color.WHITE);

        JPanel text = new JPanel(new GridLayout(2, 1));
        text.setOpaque(false);
        text.add(lblVal);
        text.add(lblDesc);

        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(new Font("Arial", Font.BOLD, 40));
        lblIcon.setForeground(new Color(255, 255, 255, 150));

        JPanel info = new JPanel(new BorderLayout());
        info.setOpaque(false);
        info.setBorder(new EmptyBorder(15, 20, 15, 20));
        info.add(lblIcon, BorderLayout.WEST);
        info.add(text, BorderLayout.EAST);

        box.add(info, BorderLayout.CENTER);
        return box;
    }

    // ================= GRAFIK =================
    class GrafikTransaksiPanel extends JPanel {

        private int[] transaksiPerJam = new int[24];

        public GrafikTransaksiPanel() {
            setBackground(Color.white);
            refresh();
        }

        public void refresh() {
            transaksiPerJam = new int[24];
            DataManager.loadTransaksi();

            for (Transaksi t : DataManager.transaksiList) {
                if (t.getTanggal().toLocalDate().equals(LocalDate.now())) {
                    transaksiPerJam[t.getTanggal().getHour()]++;
                }
            }
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            int width = getWidth();
            int height = getHeight();
            int padding = 40;
            int barWidth = (width - 2 * padding) / 24;

            int max = 1;
            for (int v : transaksiPerJam) max = Math.max(max, v);

            for (int i = 0; i < 24; i++) {
                int barHeight = (int) ((height - 2 * padding) * (transaksiPerJam[i] / (double) max));
                int x = padding + i * barWidth;
                int y = height - padding - barHeight;

                g2.setColor(new Color(52, 152, 219));
                g2.fillRect(x, y, barWidth - 4, barHeight);

                g2.setColor(Color.gray);
                g2.setFont(new Font("Arial", Font.PLAIN, 9));
                g2.drawString(String.valueOf(i), x + 2, height - 20);
            }
        }
    }
}
