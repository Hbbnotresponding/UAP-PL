package org.bengkel.util;

import org.bengkel.model.SukuCadang;
import org.bengkel.model.User;
import org.bengkel.model.Transaksi;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class DataManager {

    public static List<SukuCadang> sukuCadangList = new ArrayList<>();
    private static final String FILE_SC = "data/sukucadang.csv";
//Read
    public static void loadSukuCadang() {
        sukuCadangList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_SC))) {
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
//C /U /D - simpan ulang
    public static void saveSukuCadang() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_SC))){
            for (SukuCadang sc : sukuCadangList){
                pw.println(sc.getKode() + "," + sc.getNama() + "," + sc.getHarga());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Transaksi> transaksiList = new ArrayList<>();
    private static final String FILE_TRX = "data/transaksi.csv";

    // LOAD TRANSAKSI
    public static void loadTransaksi() {
        transaksiList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_TRX))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                transaksiList.add(
                        new Transaksi(
                                d[0],
                                LocalDateTime.parse(d[1]),
                                d[2],
                                d[3],
                                Integer.parseInt(d[4]),
                                Integer.parseInt(d[5])
                        )
                );
            }
        } catch (IOException e) {
            System.out.println("File transaksi belum ada.");
        }
    }

    public static void tambahTransaksi(Transaksi t){
        transaksiList.add(t);
        saveTransaksi();
    }

    // SAVE TRANSAKSI
    public static void saveTransaksi() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_TRX))) {
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

    public static List<User> userList = new ArrayList<>();
    private static final String FILE_USER = "data/user.csv";

    // LOAD USER
    public static void loadUser() {
        userList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_USER))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                userList.add(new User(d[0], d[1], d[2]));
            }
        } catch (IOException e) {
            System.out.println("File user belum ada.");
        }
    }

    public static User login(String username, String password){
        loadUser();

        for (User u : userList){
            if (u.getUsername().equals(username)
                   && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    }
}
