-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 07 Jan 2020 pada 09.09
-- Versi server: 10.4.6-MariaDB
-- Versi PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `scc`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_prove`
--

CREATE TABLE `detail_prove` (
  `id_detail_prove` int(5) NOT NULL,
  `id_prove` int(5) NOT NULL,
  `id_eksternal` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_prove`
--

INSERT INTO `detail_prove` (`id_detail_prove`, `id_prove`, `id_eksternal`) VALUES
(1, 1, 2),
(2, 1, 3);

-- --------------------------------------------------------

--
-- Struktur dari tabel `eksternal`
--

CREATE TABLE `eksternal` (
  `id_eksternal` int(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `akun_line` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  `angkatan` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `eksternal`
--

INSERT INTO `eksternal` (`id_eksternal`, `nama`, `no_hp`, `akun_line`, `username`, `password`, `angkatan`) VALUES
(1, 'Ali Akbar', '89121212', 'ali_akbar', 'ali_akbar', 'aliakbar123', '2018'),
(2, 'Misbakhul', '877571232', 'misbakhul', 'misbah', 'misbah', '2019');

-- --------------------------------------------------------

--
-- Struktur dari tabel `internal`
--

CREATE TABLE `internal` (
  `id_internal` int(3) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `akun_line` varchar(30) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `hak_akses` enum('Kadiv','Sekdiv','Bendahara','Staff Ahli','Manager','Staff') NOT NULL,
  `jabatan_managerial` enum('TD','HRD','PR','Kosong') NOT NULL,
  `status_sj` enum('Senior','Junior') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `internal`
--

INSERT INTO `internal` (`id_internal`, `nama`, `no_hp`, `akun_line`, `username`, `password`, `hak_akses`, `jabatan_managerial`, `status_sj`) VALUES
(1, 'Hasri Wiji Aqsari', '082234641698', 'hasriwiji08', 'hasri_wiji', 'Hasri121198', 'Kadiv', 'TD', 'Senior'),
(2, 'Tiza', '082111231232', 'tizaaja', 'tizaa', 'tizaa', 'Sekdiv', 'HRD', 'Senior'),
(4, 'Aubert Oktaviantono', '081331913541', 'kosong', 'aubert_oktaviantono@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior'),
(5, 'Yuniar Mega Kartikasari', '081905217438', 'kosong', 'yuniar_mega@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior'),
(6, 'Nauli Mazaya Siregar', '085726898883', 'kosong', 'nauli_mazaya@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior'),
(7, 'Inka Nurul Alfiana', '082336050265', 'kosong', 'inka_nurul@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior'),
(8, 'Eva Marella', '087860473988', 'kosong', 'eva_marella@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior'),
(9, 'Sarnita Sadya', '081288551389', 'kosong', 'sarnita_sadya@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior'),
(10, 'Vanda Fitriyanah', '082234755001', 'kosong', 'vanda_fitriyanah@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior'),
(12, 'Lidya Cahya Aurellia', '081332280585', 'kosong', 'lidya_cahya@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior'),
(13, 'Putri Hidayati Rohmah', '089504631309', 'kosong', 'putri_hidayati@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior'),
(14, 'Syafniya Zilfah Aniesiy', '089510777046', 'kosong', 'syafniya_zilfah@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior'),
(15, 'Kevin Agung Fernanda Rifki', '083830818118', 'kosong', 'kevin_agung@scc-himastaits.com', 'scc123', 'Staff', 'PR', 'Junior'),
(16, 'Ichwanul Kahfi Prasetya', '082335791531', 'kosong', 'ichwanul_kahfi@scc-himastaits.com', 'scc123', 'Staff', 'PR', 'Junior'),
(17, 'Shecilia Nur Salsalbila', '081217529139', 'kosong', 'shecilia_salsabila@scc-himastaits.com', 'scc123', 'Staff', 'PR', 'Junior'),
(18, 'Seza Dwiwulan Ramadini', '082336964693', 'kosong', 'seza_dwiwulan@scc-himastaits.com', 'scc123', 'Bendahara', 'Kosong', 'Junior');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jadwal_bank_software`
--

CREATE TABLE `jadwal_bank_software` (
  `id_jadwal_bs` int(3) NOT NULL,
  `id_internal` int(3) NOT NULL,
  `hari` enum('Senin','Selasa','Rabu','Kamis','Jumat') NOT NULL,
  `jam_mulai` varchar(10) NOT NULL,
  `jam_selesai` varchar(10) NOT NULL,
  `status_booking` enum('Free','Unfree') NOT NULL,
  `status_aktif` enum('Aktif','Tidak Aktif') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `jadwal_bank_software`
--

INSERT INTO `jadwal_bank_software` (`id_jadwal_bs`, `id_internal`, `hari`, `jam_mulai`, `jam_selesai`, `status_booking`, `status_aktif`) VALUES
(1, 1, 'Senin', '07:00', '09:00', 'Free', 'Aktif'),
(2, 1, 'Selasa', '08:30', '09:30', 'Free', 'Aktif');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jadwal_prove`
--

CREATE TABLE `jadwal_prove` (
  `id_jadwal_prove` int(3) NOT NULL,
  `id_internal` int(3) NOT NULL,
  `hari` enum('Senin','Selasa','Rabu','Kamis','Jumat') NOT NULL,
  `jam_mulai` varchar(10) NOT NULL,
  `jam_selesai` varchar(10) NOT NULL,
  `status_booking` enum('Free','Unfree') NOT NULL,
  `status_aktif` enum('Aktif','Tidak Aktif') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `jadwal_prove`
--

INSERT INTO `jadwal_prove` (`id_jadwal_prove`, `id_internal`, `hari`, `jam_mulai`, `jam_selesai`, `status_booking`, `status_aktif`) VALUES
(1, 1, 'Senin', '07:00', '09:00', 'Free', 'Aktif'),
(2, 1, 'Selasa', '11:00', '12:00', 'Free', 'Aktif'),
(3, 1, 'Rabu', '13:00', '15:00', 'Free', 'Aktif'),
(5, 1, 'Kamis', '08:30', '09:30', 'Free', 'Aktif'),
(6, 1, 'Jumat', '14:30', '15:30', 'Free', 'Aktif'),
(7, 1, 'Senin', '12:00', '13:00', 'Free', 'Tidak Aktif'),
(8, 1, 'Selasa', '07:00', '09:00', 'Free', 'Aktif'),
(9, 1, 'Rabu', '07:00', '09:00', 'Free', 'Aktif');

-- --------------------------------------------------------

--
-- Struktur dari tabel `materi_prove`
--

CREATE TABLE `materi_prove` (
  `id_materi_prove` int(3) NOT NULL,
  `nama` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `materi_prove`
--

INSERT INTO `materi_prove` (`id_materi_prove`, `nama`) VALUES
(3, 'Excel');

-- --------------------------------------------------------

--
-- Struktur dari tabel `prove`
--

CREATE TABLE `prove` (
  `id_prove` int(5) NOT NULL,
  `id_eksternal` int(3) NOT NULL,
  `id_internal` int(3) NOT NULL,
  `id_materi_prove` int(3) NOT NULL,
  `deskripsi_materi` tinytext NOT NULL,
  `status_prove` enum('Selesai','Belum Selesai') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `prove`
--

INSERT INTO `prove` (`id_prove`, `id_eksternal`, `id_internal`, `id_materi_prove`, `deskripsi_materi`, `status_prove`) VALUES
(1, 1, 1, 3, 'Bab rumus micro', 'Belum Selesai');

-- --------------------------------------------------------

--
-- Struktur dari tabel `software`
--

CREATE TABLE `software` (
  `id_software` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `software`
--

INSERT INTO `software` (`id_software`, `nama`) VALUES
(1, 'Accessx');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `detail_prove`
--
ALTER TABLE `detail_prove`
  ADD PRIMARY KEY (`id_detail_prove`);

--
-- Indeks untuk tabel `eksternal`
--
ALTER TABLE `eksternal`
  ADD PRIMARY KEY (`id_eksternal`);

--
-- Indeks untuk tabel `internal`
--
ALTER TABLE `internal`
  ADD PRIMARY KEY (`id_internal`);

--
-- Indeks untuk tabel `jadwal_bank_software`
--
ALTER TABLE `jadwal_bank_software`
  ADD PRIMARY KEY (`id_jadwal_bs`);

--
-- Indeks untuk tabel `jadwal_prove`
--
ALTER TABLE `jadwal_prove`
  ADD PRIMARY KEY (`id_jadwal_prove`);

--
-- Indeks untuk tabel `materi_prove`
--
ALTER TABLE `materi_prove`
  ADD PRIMARY KEY (`id_materi_prove`);

--
-- Indeks untuk tabel `prove`
--
ALTER TABLE `prove`
  ADD PRIMARY KEY (`id_prove`);

--
-- Indeks untuk tabel `software`
--
ALTER TABLE `software`
  ADD PRIMARY KEY (`id_software`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `detail_prove`
--
ALTER TABLE `detail_prove`
  MODIFY `id_detail_prove` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `eksternal`
--
ALTER TABLE `eksternal`
  MODIFY `id_eksternal` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `internal`
--
ALTER TABLE `internal`
  MODIFY `id_internal` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT untuk tabel `jadwal_bank_software`
--
ALTER TABLE `jadwal_bank_software`
  MODIFY `id_jadwal_bs` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `jadwal_prove`
--
ALTER TABLE `jadwal_prove`
  MODIFY `id_jadwal_prove` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `materi_prove`
--
ALTER TABLE `materi_prove`
  MODIFY `id_materi_prove` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `prove`
--
ALTER TABLE `prove`
  MODIFY `id_prove` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `software`
--
ALTER TABLE `software`
  MODIFY `id_software` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
