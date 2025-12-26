package org.bengkel.util;

import org.bengkel.model.SukuCadang;
import org.bengkel.model.Transaksi;
import org.bengkel.model.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    public static List<User> users = new ArrayList<>();
    public static List<SukuCadang> sukuCadangList = new ArrayList<>();
    public static List<Transaksi> transaksiList = new ArrayList<>();

    // ================= USER =================
    public static void loadUsers() {
        users.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("data/user.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                users.add(new User(d[0], d[1], d[2]));
            }
        } catch (IOException e) {
            System.out.println("File user belum ada.");
        }
    }

    public static User login(String u, String p) {
        for (User user : users) {
            if (user.getUsername().equals(u) && user.getPassword().equals(p)) {
                return user;
            }
        }
        return null;
    }

    // ================= SUKU CADANG =================
    public static void loadSukuCadang() {
        sukuCadangList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("data/sukucadang.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                sukuCadangList.add(
                        new SukuCadang(d[0], d[1], Integer.parseInt(d[2]))
                );
            }
        } catch (IOException e) {
            System.out.println("File suku cadang belum ada.");
        }
    }

    // ================= SUKU CADANG =================
    public static void saveSukuCadang() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("data/sukucadang.csv"))) {
            for (SukuCadang sc : sukuCadangList) {
                pw.println(
                        sc.getKode() + "," +
                                sc.getNama() + "," +
                                sc.getHarga()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // ================= TRANSAKSI =================
    public static void tambahTransaksi(Transaksi t) {
        transaksiList.add(t);
    }

    public static void saveTransaksi() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("data/transaksi.csv"))) {
            for (Transaksi t : transaksiList) {
                pw.println(
                        t.getId() + "," +
                                t.getTanggal() + "," +
                                t.getKodeSC() + "," +
                                t.getNamaSC() + "," +
                                t.getJumlah() + "," +
                                t.getTotal()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadTransaksi() {
        transaksiList.clear();
        File file = new File("data/transaksi.csv");
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                transaksiList.add(new Transaksi(
                        d[0],
                        LocalDateTime.parse(d[1]),
                        d[2],
                        d[3],
                        Integer.parseInt(d[4]),
                        Integer.parseInt(d[5])
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Transaksi getLastTransaksi() {
        if (transaksiList == null || transaksiList.isEmpty()) {
            return null;
        }
        return transaksiList.get(transaksiList.size() - 1);
    }

}
