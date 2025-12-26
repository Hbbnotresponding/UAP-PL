# 🛠️ Sistem Informasi Bengkel Mobil
(Ujian Akhir Praktikum Pemrograman Lanjut)

Aplikasi **Sistem Informasi Bengkel Mobil** berbasis **Java Swing** yang dibuat
untuk memenuhi tugas **Ujian Akhir Praktikum (UAP) Pemrograman Lanjut**.

Aplikasi ini menerapkan konsep **OOP**, **GUI Java Swing**, dan **manajemen data**
dengan dua jenis pengguna: **Admin** dan **User**.

---

## 📌 Fitur Aplikasi

### 👤 1. User (Kasir / Pengguna)
- Login User
- Input transaksi servis kendaraan
- Mengisi data pelanggan dan kendaraan
- Melihat riwayat transaksi servis
- Melihat detail transaksi / struk

### 🔑 2. Admin
- Login Admin
- Dashboard Admin (Beranda)
- Melihat:
    - Jumlah transaksi hari ini
    - Total pendapatan hari ini
- Grafik transaksi harian (berdasarkan jam)
- Melihat seluruh data transaksi
- Melihat data user / kasir

---

## 📊 Fitur Unggulan
- Antarmuka grafis menggunakan **Java Swing**
- Dashboard Admin dengan statistik real-time
- Grafik transaksi harian
- Pemisahan role Admin dan User
- Struktur project rapi (Model, UI, Util)
- Pengelolaan data terpusat menggunakan `DataManager`

---

## 🗂️ Struktur Project

UAP_PL
├── src
│   └── main
│       └── java
│            └── org.bengkel
│                ├── model
│                │   ├── User.java
│                │   ├── Transaksi.java
│                │   └── Suku Cadang
│                ├── ui
│                │ ├── LoginFrame.java
│                │ ├── AdminDashboard.java
│                │ ├── AdminHomePanel.java
│                │ ├── UserDashboard.java
│                │ └── ...
│                └── util
│                     └── DataManager.java
└── README.md


---

## 🚀 Cara Menjalankan Program

### 1️⃣ Persiapan
- Pastikan **JDK sudah terinstall**
- Gunakan **IntelliJ IDEA** (disarankan)
- Sistem Operasi: Windows / Linux / macOS

### 2️⃣ Clone Repository
```bash
git clone https://github.com/Hbbnotresponding/UAP-PL.git

3️⃣ Buka Project
1. Buka IntelliJ IDEA
2. Pilih Open Project
3. Arahkan ke folder UAP_PL

4️⃣ Jalankan Program
Buka file:
LoginFrame.java
Klik Run ▶

🔐 Akun Login (Contoh)
Admin
Username: admin
Password: admin
User
Username: user
Password: user
(akun dapat disesuaikan di DataManager)

🧠Konsep yang Digunakan
Object Oriented Programming (OOP)
Java Swing GUI
Event Handling
Collection (ArrayList)
Branching Git (feature-admin, feature-user)

🌿 Manajemen Branch Git
main → branch utama
feature-admin → pengembangan fitur admin
feature-user → pengembangan fitur user
feature-login → pengembangan fitur login

✍️ Author
Habib Anoraga Al Islami
Mahasiswa – UMM Prodi Informatika 24

📌 Catatan
Aplikasi ini dibuat untuk keperluan akademik dan masih dapat
dikembangkan lebih lanjut, seperti:
Integrasi database
Export laporan ke Excel
Sistem login berbasis database