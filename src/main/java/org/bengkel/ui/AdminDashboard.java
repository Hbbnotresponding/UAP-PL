package org.bengkel.ui;

import org.bengkel.model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminDashboard extends JFrame {

    private JPanel contentCards;
    private CardLayout cardLayout;

    public AdminDashboard(User user) {

        setTitle("Sistem Informasi Bengkel - UMM Motors");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= WARNA =================
        Color MERAH = new Color(137, 0, 37);
        Color ORANGE = new Color(243, 156, 18);
        Color PUTIH = Color.WHITE;
        Color BG = new Color(236, 240, 245);

        // ================= NAVBAR =================
        JPanel navbar = new JPanel(new BorderLayout());
        navbar.setBackground(MERAH);
        navbar.setPreferredSize(new Dimension(0, 80));
        navbar.setBorder(new EmptyBorder(0, 20, 0, 20));

        // ===== KIRI (LOGO + BRAND) =====
        JPanel navLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        navLeft.setOpaque(false);

        JLabel logoLabel = new JLabel();
        URL logoURL = getClass().getClassLoader()
                .getResource("image/logo_bengkel.png");

        if (logoURL != null) {
            ImageIcon icon = new ImageIcon(logoURL);
            Image img = icon.getImage();

            int targetHeight = 45;
            int targetWidth = (img.getWidth(null) * targetHeight)
                    / img.getHeight(null);

            Image scaled = img.getScaledInstance(
                    targetWidth,
                    targetHeight,
                    Image.SCALE_SMOOTH
            );

            logoLabel.setIcon(new ImageIcon(scaled));
        } else {
            System.out.println("⚠ Logo tidak ditemukan!");
        }

        JLabel brand = new JLabel("UMM MOTORS");
        brand.setForeground(Color.WHITE);
        brand.setFont(new Font("Segoe UI", Font.BOLD, 26));

        navLeft.add(logoLabel);
        navLeft.add(brand);

        // ===== KANAN =====
        JLabel adminInfo = new JLabel("Administrator ▼");
        adminInfo.setForeground(Color.WHITE);
        adminInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        navbar.add(navLeft, BorderLayout.WEST);
        navbar.add(adminInfo, BorderLayout.EAST);

        // ================= SIDEBAR =================
        JPanel sidebar = new JPanel();
        sidebar.setBackground(PUTIH);
        sidebar.setPreferredSize(new Dimension(260, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JPanel sideHeader = new JPanel(new GridLayout(2, 1));
        sideHeader.setBackground(ORANGE);
        sideHeader.setMaximumSize(new Dimension(260, 110));
        sideHeader.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel lblApp = new JLabel("Informasi Bengkel");
        lblApp.setForeground(Color.WHITE);
        lblApp.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel lblDate = new JLabel(
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern(
                                "EEEE, dd MMMM yyyy HH:mm")
                )
        );
        lblDate.setForeground(Color.WHITE);
        lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        sideHeader.add(lblApp);
        sideHeader.add(lblDate);

        sidebar.add(sideHeader);
        sidebar.add(Box.createVerticalStrut(10));

        // ================= CONTENT =================
        cardLayout = new CardLayout();
        contentCards = new JPanel(cardLayout);
        contentCards.setBackground(BG);

        AdminHomePanel homePanel = new AdminHomePanel();

        contentCards.add(homePanel, "Beranda");
        contentCards.add(new FormSukuCadang(), "Suku Cadang");
        contentCards.add(new FormRiwayatTransaksi(), "Riwayat Transaksi");
        contentCards.add(new DataKasirPanel(), "Data Kasir");
        contentCards.add(new LaporanAdminPanel(), "Laporan");

        // ================= MENU =================
        String[] menus = {
                "Beranda",
                "Suku Cadang",
                "Riwayat Transaksi",
                "Data Kasir",
                "Laporan"
        };

        for (String m : menus) {
            JButton btn = createMenuButton(m);

            btn.addActionListener(e -> {
                cardLayout.show(contentCards, m);
                if (m.equals("Beranda")) {
                    homePanel.refreshData();
                }
            });

            sidebar.add(btn);
        }

        // ================= FOOTER =================
        JPanel footer = new JPanel();
        footer.setBackground(MERAH);
        footer.setPreferredSize(new Dimension(0, 30));

        JLabel lblFooter = new JLabel("BENGKEL UMM MOTORS");
        lblFooter.setForeground(Color.WHITE);
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        footer.add(lblFooter);

        // ================= ADD =================
        add(navbar, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(contentCards, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        cardLayout.show(contentCards, "Beranda");
        setVisible(true);
    }

    // ================= MENU BUTTON =================
    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(260, 45));
        btn.setBackground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new MatteBorder(0, 0, 1, 0,
                new Color(220, 220, 220)));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(230, 240, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(Color.WHITE);
            }
        });

        return btn;
    }
}
