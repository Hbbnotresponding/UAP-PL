package org.bengkel.model;

public class SukuCadang {
    private String kode;
    private String nama;
    private int harga;

    public SukuCadang(String kode, String nama, int harga){
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga = harga;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
