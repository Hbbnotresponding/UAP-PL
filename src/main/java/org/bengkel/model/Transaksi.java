package org.bengkel.model;

import java.time.LocalDateTime;

public class Transaksi {

    private String id;
    private LocalDateTime tanggal;
    private String kodeSC;
    private String namaSC;
    private int jumlah;
    private int total;

    public Transaksi(String id,
                     LocalDateTime tanggal,
                     String kodeSC,
                     String namaSC,
                     int jumlah,
                     int total)
                    {

        this.id = id;
        this.tanggal = tanggal;
        this.kodeSC = kodeSC;
        this.namaSC = namaSC;
        this.jumlah = jumlah;
        this.total = total;

    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTanggal() {
        return tanggal;
    }

    public String getKodeSC() {
        return kodeSC;
    }

    public String getNamaSC() {
        return namaSC;
    }

    public int getJumlah() {
        return jumlah;
    }

    public int getTotal() {
        return total;
    }

}
