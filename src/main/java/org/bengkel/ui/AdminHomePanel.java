package org.bengkel.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminHomePanel extends JPanel {

    public AdminHomePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(236, 240, 245));
        setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel titleHome = new JLabel("Beranda");
        titleHome.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        add(titleHome, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        statsPanel.setOpaque(false);
        statsPanel.setPreferredSize(new Dimension(0, 150));

        statsPanel.add(createStatBox("0", "Transaksi hari ini", "✔", new Color(243,156,18)));
        statsPanel.add(createStatBox("Rp.0", "Pendapatan hari ini", "+", new Color(243,156,18)));

        JPanel graphContainer = new JPanel(new BorderLayout());
        graphContainer.setBackground(Color.WHITE);
        graphContainer.setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));

        JLabel graphTitle = new JLabel("GRAFIK TRANSAKSI", JLabel.CENTER);
        graphTitle.setBorder(new EmptyBorder(15,0,15,0));
        graphTitle.setFont(new Font("Arial", Font.BOLD, 14));

        graphContainer.add(graphTitle, BorderLayout.NORTH);
        graphContainer.add(new JPanel(), BorderLayout.CENTER);

        JPanel centerBody = new JPanel(new BorderLayout(0, 30));
        centerBody.setOpaque(false);
        centerBody.add(statsPanel, BorderLayout.NORTH);
        centerBody.add(graphContainer, BorderLayout.CENTER);

        add(centerBody, BorderLayout.CENTER);
    }

    private JPanel createStatBox(String val, String desc, String icon, Color col) {
        JPanel box = new JPanel(new BorderLayout());
        box.setBackground(col);

        JLabel lblVal = new JLabel(val, JLabel.RIGHT);
        lblVal.setFont(new Font("Arial", Font.BOLD, 35));
        lblVal.setForeground(Color.WHITE);

        JLabel lblDesc = new JLabel(desc, JLabel.RIGHT);
        lblDesc.setForeground(Color.WHITE);

        JPanel text = new JPanel(new GridLayout(2,1));
        text.setOpaque(false);
        text.add(lblVal);
        text.add(lblDesc);

        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(new Font("Arial", Font.BOLD, 40));
        lblIcon.setForeground(new Color(255,255,255,150));

        JPanel info = new JPanel(new BorderLayout());
        info.setOpaque(false);
        info.setBorder(new EmptyBorder(15,20,15,20));
        info.add(lblIcon, BorderLayout.WEST);
        info.add(text, BorderLayout.EAST);

        box.add(info, BorderLayout.CENTER);
        return box;
    }
}
