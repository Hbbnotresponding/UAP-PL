package org.bengkel.ui;

import org.bengkel.util.DataManager;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserDashboard extends JFrame {

    public UserDashboard() {
        setTitle("Dashboard Pelanggan - UMM Motors");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== WARNA TEMA =====
        Color tosca = new Color(0, 137, 132);
        Color biru = new Color(52, 152, 219);
        Color orange = new Color(243, 156, 18);
        Color bg = new Color(245, 247, 250);

        // ================= HEADER =================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(tosca);
        header.setPreferredSize(new Dimension(0, 55));
        header.setBorder(new EmptyBorder(0, 20, 0, 20));

        JLabel lblTitle = new JLabel("UMM MOTORS");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblDate = new JLabel(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm"))
        );
        lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDate.setForeground(Color.WHITE);

        header.add(lblTitle, BorderLayout.WEST);
        header.add(lblDate, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // ================= SIDEBAR =================
        JPanel sidebar = new JPanel();
        sidebar.setBackground(biru);
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(20, 10, 20, 10));

        sidebar.add(createMenuButton("🏠  Beranda"));
        JButton btnRiwayat = createMenuButton("Riwayat Servis");
        btnRiwayat.addActionListener(e -> new RiwayatServisFrame());
        sidebar.add(btnRiwayat);
        setVisible(true);

        JButton btnStruk = createMenuButton("Lihat Struk");
        btnStruk.addActionListener(e ->{
            DataManager.loadSukuCadang();
            DataManager.loadTransaksi();
            new FormTransaksi();
        });

        sidebar.add(btnStruk);

        add(sidebar, BorderLayout.WEST);

        setVisible(true); // <-- INI WAJIB


        // ================= CONTENT =================
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(bg);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblHeader = new JLabel("Beranda");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
        content.add(lblHeader, BorderLayout.NORTH);

        // ===== CARD AREA =====
        JPanel cardPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        cardPanel.setOpaque(false);
        cardPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        cardPanel.add(createCard(
                "Riwayat Servis",
                "Lihat daftar servis kendaraan Anda",
                orange,
                () -> new RiwayatServisFrame()
        ));

        cardPanel.add(createCard(
                "Lihat Struk",
                "Cetak struk pembayaran terakhir",
                biru,
                () -> {
                    DataManager.loadSukuCadang();
                    DataManager.loadTransaksi();
                    new FormTransaksi();
                }
        ));

        content.add(cardPanel, BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);

        add(content, BorderLayout.CENTER);
        setVisible(true);
    }

    // ================= MENU BUTTON =================
    private JButton createMenuButton(String text) {
        Color normal = Color.WHITE;
        Color hover = new Color(230, 240, 250);

        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setBackground(normal);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 15, 8, 15));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(normal);
            }
        });

        return btn;
    }

    // ================= CARD =================
    private JPanel createCard(String title, String desc, Color accent, Runnable action) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(20, 20, 20, 20)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblDesc = new JLabel(desc);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDesc.setForeground(Color.GRAY);
        lblDesc.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblLine = new JLabel(" ");
        lblLine.setOpaque(true);
        lblLine.setBackground(accent);
        lblLine.setMaximumSize(new Dimension(Integer.MAX_VALUE, 4));

        card.add(lblTitle);
        card.add(Box.createVerticalStrut(5));
        card.add(lblDesc);
        card.add(Box.createVerticalStrut(15));
        card.add(lblLine);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                action.run();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(252, 252, 252));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
            }
        });

        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UserDashboard();
        });
    }


}
