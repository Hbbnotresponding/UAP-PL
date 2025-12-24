package org.bengkel.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminDashboard extends JFrame {

    private JPanel contentCards;
    private CardLayout cardLayout;

    public AdminDashboard() {
        setTitle("Sistem Informasi Bengkel - UMM Motors");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= WARNA =================
        Color colorTosca = new Color(0, 137, 132);
        Color colorSidebar = new Color(51, 122, 183);
        Color colorOrange = new Color(243, 156, 18);
        Color colorBg = new Color(236, 240, 245);

        // ================= NAVBAR =================
        JPanel navbar = new JPanel(new BorderLayout());
        navbar.setBackground(colorTosca);
        navbar.setPreferredSize(new Dimension(5, 60));

        JLabel brand = new JLabel("  = UMM MOTORS =", JLabel.LEFT);
        brand.setForeground(Color.WHITE);
        brand.setFont(new Font("Segoe UI", Font.BOLD, 37));

        JLabel adminInfo = new JLabel("Administrator ▼  ", JLabel.RIGHT);
        adminInfo.setForeground(Color.WHITE);

        navbar.add(brand, BorderLayout.WEST);
        navbar.add(adminInfo, BorderLayout.EAST);

        // ================= SIDEBAR =================
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(colorSidebar);
        sidebarPanel   .setBorder(null);
        sidebarPanel.setPreferredSize(new Dimension(250, 0));
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));

        JPanel sideHeader = new JPanel(new GridLayout(2, 1));
        sideHeader.setBackground(colorOrange);
        sideHeader.setMaximumSize(new Dimension(375, 100));
        sideHeader.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel lblApp = new JLabel("Informasi Bengkel");
        lblApp.setForeground(Color.WHITE);
        lblApp.setFont(new Font("Segoe UI", Font.BOLD, 24));

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm");

        JLabel lblDate = new JLabel(now.format(formatter));
        lblDate.setForeground(Color.WHITE);
        lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        sideHeader.add(lblApp);
        sideHeader.add(lblDate);
        sidebarPanel.add(sideHeader);

        // ================= CARD LAYOUT =================
        cardLayout = new CardLayout();
        FormSukuCadang formSukuCadang = new FormSukuCadang();
        contentCards = new JPanel(cardLayout);
        contentCards.setBackground(colorBg);

        // ================= CARD =================
        AdminHomePanel homePanel = new AdminHomePanel();
        contentCards.add(homePanel, "Beranda");
        contentCards.add(formSukuCadang, "FormSukuCadang");

        RiwayatTransaksiAdminPanel riwayatPanel = new RiwayatTransaksiAdminPanel();
        contentCards.add(riwayatPanel, "Riwayat Transaksi");

        DataKasirPanel dataKasirPanel = new DataKasirPanel();
        contentCards.add(dataKasirPanel, "Data Kasir");

        LaporanAdminPanel laporanPanel = new LaporanAdminPanel();
        contentCards.add(laporanPanel, "Laporan");

        // ================= MENU =================
        String[] menuItems = {
                "Beranda",
                "Suku Cadang",
                "Riwayat Transaksi",
                "Data Kasir",
                "Laporan"
        };

        for (String item : menuItems) {
            JButton btn = createMenuButton(item, colorSidebar);

            btn.addActionListener(e -> {
                switch (item) {
                    case "Beranda":
                        cardLayout.show(contentCards, "Beranda");
                        break;
                    case "Suku Cadang":
                        cardLayout.show(contentCards, "FormSukuCadang");
                        break;
                    case "Riwayat Transaksi":
                        cardLayout.show(contentCards, "Riwayat Transaksi");
                        break;
                    case "Data Kasir":
                        cardLayout.show(contentCards, "Data Kasir");
                        break;
                    case "Laporan":
                        cardLayout.show(contentCards, "Laporan");
                        break;
                }
            });

            sidebarPanel.add(btn);
        }

        // ================= FOOTER =================
        JPanel footer = new JPanel();
        footer.setBackground(colorTosca);

        JLabel lblFooter = new JLabel("BENGKEL UMM MOTORS");
        lblFooter.setForeground(Color.WHITE);
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 11));
        footer.add(lblFooter);

        // ================= FINAL =================
        add(navbar, BorderLayout.NORTH);
        add(sidebarPanel, BorderLayout.WEST);
        add(contentCards, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        cardLayout.show(contentCards, "Beranda");
        setVisible(true);
    }

    // ================= HELPER =================
    private JButton createMenuButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setMargin(new Insets(5, 15, 5, 5));
        btn.setMaximumSize(new Dimension(350, 50));
        btn.setMinimumSize(new Dimension(350, 50));
        btn.setForeground(new Color(5, 5, 5));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new MatteBorder(0, 0, 1, 0, bg.darker()));
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(bg.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(Color.WHITE);
            }
        });

        return btn;
    }

    private JPanel createStatBox(String val, String desc, String icon, Color col) {
        JPanel box = new JPanel(new BorderLayout());
        box.setBackground(col);

        JPanel info = new JPanel(new BorderLayout());
        info.setOpaque(false);
        info.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel lblIcon = new JLabel(icon);
        lblIcon.setFont(new Font("Arial", Font.BOLD, 40));
        lblIcon.setForeground(new Color(255, 255, 255, 150));

        JLabel lblVal = new JLabel(val, JLabel.RIGHT);
        lblVal.setFont(new Font("Arial", Font.BOLD, 35));
        lblVal.setForeground(Color.WHITE);

        JLabel lblDesc = new JLabel(desc, JLabel.RIGHT);
        lblDesc.setForeground(Color.WHITE);

        JPanel textGroup = new JPanel(new GridLayout(2, 1));
        textGroup.setOpaque(false);
        textGroup.add(lblVal);
        textGroup.add(lblDesc);

        info.add(lblIcon, BorderLayout.WEST);
        info.add(textGroup, BorderLayout.EAST);

        JPanel link = new JPanel();
        link.setBackground(new Color(0, 0, 0, 40));
        JLabel lblLink = new JLabel("Lihat Rincian →");
        lblLink.setForeground(Color.WHITE);
        link.add(lblLink);

        box.add(info, BorderLayout.CENTER);
        box.add(link, BorderLayout.SOUTH);

        return box;
    }

    public static void main(String[] args) {
        new AdminDashboard();
    }
}
