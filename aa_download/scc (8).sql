-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 25 Feb 2020 pada 14.31
-- Versi server: 10.1.37-MariaDB
-- Versi PHP: 7.3.0

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
-- Struktur dari tabel `bank_software`
--

CREATE TABLE `bank_software` (
  `kode_bank_s` char(12) NOT NULL,
  `id_eksternal` char(5) NOT NULL,
  `id_jadwal_bs` int(3) NOT NULL,
  `tanggal_booking` date NOT NULL,
  `tanggal_bs` date NOT NULL,
  `status_bs` enum('Selesai','Belum Selesai','Batal') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `bank_software`
--

INSERT INTO `bank_software` (`kode_bank_s`, `id_eksternal`, `id_jadwal_bs`, `tanggal_booking`, `tanggal_bs`, `status_bs`) VALUES
('BS200221-001', 'EK001', 2, '2020-02-21', '2020-02-20', 'Batal'),
('BS200221-002', 'EK001', 3, '2020-02-21', '2020-02-22', 'Selesai'),
('BS200221-003', 'EK001', 2, '2020-02-21', '2020-02-25', 'Selesai'),
('BS200221-006', 'EK002', 1, '2020-02-21', '2020-02-23', 'Batal'),
('BS200224-001', 'EK001', 1, '2020-02-24', '2020-02-23', 'Batal'),
('BS200224-002', 'EK002', 3, '2020-02-24', '2020-02-26', 'Belum Selesai');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_bs`
--

CREATE TABLE `detail_bs` (
  `id_detail_bs` int(11) NOT NULL,
  `kode_bank_s` char(12) NOT NULL,
  `id_software` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_bs`
--

INSERT INTO `detail_bs` (`id_detail_bs`, `kode_bank_s`, `id_software`) VALUES
(1, 'BS200221-001', 3),
(2, 'BS200221-001', 2),
(3, 'BS200221-003', 1),
(9, 'BS200221-006', 2),
(10, 'BS200221-006', 3),
(11, 'BS200224-001', 2),
(12, 'BS200224-001', 3),
(13, 'BS200224-001', 1),
(14, 'BS200224-001', 2),
(15, 'BS200224-002', 2),
(16, 'BS200224-002', 2),
(17, 'BS200224-002', 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_prove`
--

CREATE TABLE `detail_prove` (
  `id_detail_prove` int(5) NOT NULL,
  `id_prove` char(10) NOT NULL,
  `id_eksternal` char(5) NOT NULL,
  `rating` int(1) NOT NULL,
  `deskripsi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_prove`
--

INSERT INTO `detail_prove` (`id_detail_prove`, `id_prove`, `id_eksternal`, `rating`, `deskripsi`) VALUES
(1, '200219-001', 'EK001', 0, '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `eksternal`
--

CREATE TABLE `eksternal` (
  `id_eksternal` char(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `akun_line` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  `angkatan` varchar(4) NOT NULL,
  `foto` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `eksternal`
--

INSERT INTO `eksternal` (`id_eksternal`, `nama`, `no_hp`, `akun_line`, `username`, `password`, `angkatan`, `foto`) VALUES
('EK001', 'a', '6', 'a', '062', 'a', '2017', 'DEFEK'),
('EK002', 'b', '494964', 'ahha', '0621', 'b', '2018', 'DEFEK');

-- --------------------------------------------------------

--
-- Struktur dari tabel `internal`
--

CREATE TABLE `internal` (
  `id_internal` char(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `akun_line` varchar(30) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `hak_akses` enum('Kadiv','Sekdiv','Bendahara','Staff Ahli','Manager','Staff') NOT NULL,
  `jabatan_managerial` enum('TD','HRD','PR','Kosong') NOT NULL,
  `status_sj` enum('Senior','Junior') NOT NULL,
  `foto` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `internal`
--

INSERT INTO `internal` (`id_internal`, `nama`, `no_hp`, `akun_line`, `username`, `password`, `hak_akses`, `jabatan_managerial`, `status_sj`, `foto`) VALUES
('IN001', 'Hasri Wiji Aqsari', '082234641698', 'hasriwiji08', 'a', 'a', 'Kadiv', 'TD', 'Senior', 'DEFIN'),
('IN002', 'Vanda Fitriyanah', '082234755001', 'kosong', 'vanda_fitriyanah@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior', 'DEFIN'),
('IN003', 'Lidya Cahya Aurellia', '081332280585', 'kosong', 'lidya_cahya@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior', 'DEFIN'),
('IN004', 'Putri Hidayati Rohmah', '089504631309', 'kosong', 'putri_hidayati@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior', 'DEFIN'),
('IN005', 'Syafniya Zilfah Aniesiy', '089510777046', 'kosong', 'syafniya_zilfah@scc-himastaits.com', 'scc123', 'Staff', 'HRD', 'Junior', 'DEFIN'),
('IN006', 'Kevin Agung Fernanda Rifki', '083830818118', 'kosong', 'kevin_agung@scc-himastaits.com', 'scc123', 'Staff', 'PR', 'Junior', 'DEFIN'),
('IN007', 'Ichwanul Kahfi Prasetya', '082335791531', 'kosong', 'bb', 'b', 'Staff', 'PR', 'Junior', 'DEFIN'),
('IN008', 'Shecilia Nur Salsalbila', '081217529139', 'kosong', 'shecilia_salsabila@scc-himastaits.com', 'scc123', 'Staff', 'PR', 'Junior', 'DEFIN'),
('IN009', 'Seza Dwiwulan Ramadini', '082336964693', 'kosong', 'seza_dwiwulan@scc-himastaits.com', 'scc123', 'Bendahara', 'Kosong', 'Junior', 'DEFIN'),
('IN010', 'Tiza a', '082111231232', 'tizaaja', 'tizaa', 'aa', 'Sekdiv', 'HRD', 'Senior', 'IN010'),
('IN011', 'Aubert Oktaviantono', '081331913541', 'kosong', 'aubert_oktaviantono@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('IN012', 'Yuniar Mega Kartikasari', '081905217438', 'kosong', 'yuniar_mega@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('IN013', 'Nauli Mazaya Siregar', '085726898883', 'kosong', 'nauli_mazaya@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('IN014', 'Inka Nurul Alfiana', '082336050265', 'kosong', 'inka_nurul@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('IN015', 'Eva Marella', '087860473988', 'kosong', 'eva_marella@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN'),
('IN016', 'Sarnita Sadya', '081288551389', 'kosong', 'sarnita_sadya@scc-himastaits.com', 'scc123', 'Staff', 'TD', 'Junior', 'DEFIN');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jadwal_bank_software`
--

CREATE TABLE `jadwal_bank_software` (
  `id_jadwal_bs` int(3) NOT NULL,
  `id_internal` char(5) NOT NULL,
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
(1, 'IN007', 'Senin', '07:00', '09:00', 'Free', 'Aktif'),
(2, 'IN007', 'Selasa', '08:30', '09:30', 'Free', 'Aktif'),
(3, 'IN007', 'Rabu', '09:30', '09:45', 'Free', 'Aktif'),
(4, 'IN007', 'Kamis', '07:00', '07:00', 'Free', 'Aktif');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jadwal_prove`
--

CREATE TABLE `jadwal_prove` (
  `id_jadwal_prove` int(3) NOT NULL,
  `id_internal` char(5) NOT NULL,
  `hari` enum('Senin','Selasa','Rabu','Kamis','Jumat') NOT NULL,
  `jam_mulai` varchar(10) NOT NULL,
  `jam_selesai` varchar(10) NOT NULL,
  `status_booking` enum('Free','Unfree') NOT NULL,
  `status_aktif` enum('Aktif','Tidak Aktif') NOT NULL,
  `terakhir_dibooking` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `jadwal_prove`
--

INSERT INTO `jadwal_prove` (`id_jadwal_prove`, `id_internal`, `hari`, `jam_mulai`, `jam_selesai`, `status_booking`, `status_aktif`, `terakhir_dibooking`) VALUES
(1, 'IN001', 'Senin', '07:00', '09:00', 'Free', 'Aktif', '2020-02-06'),
(2, 'IN001', 'Selasa', '11:00', '12:00', 'Free', 'Aktif', '2020-02-06'),
(3, 'IN001', 'Rabu', '13:00', '15:00', 'Free', 'Aktif', '2020-02-06'),
(5, 'IN001', 'Kamis', '08:30', '09:30', 'Free', 'Aktif', '2020-02-06'),
(6, 'IN001', 'Jumat', '14:30', '15:30', 'Free', 'Aktif', '2020-02-06'),
(7, 'IN001', 'Senin', '12:00', '13:00', 'Unfree', 'Aktif', '2020-02-19'),
(8, 'IN001', 'Selasa', '07:00', '09:00', 'Free', 'Aktif', '2020-02-02'),
(9, 'IN001', 'Rabu', '07:00', '09:00', 'Free', 'Aktif', '2020-02-06');

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_anggota_prove`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_anggota_prove` (
`id_detail_prove` int(5)
,`id_prove` char(10)
,`rating` int(1)
,`deskripsi` text
,`id_eksternal` char(5)
,`nama` varchar(50)
,`no_hp` varchar(15)
,`akun_line` varchar(30)
,`username` varchar(30)
,`angkatan` varchar(4)
,`foto` char(5)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_bank_software`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_bank_software` (
`kode_bank_s` char(12)
,`id_eksternal` char(5)
,`nama` varchar(50)
,`no_hp` varchar(15)
,`akun_line` varchar(30)
,`angkatan` varchar(4)
,`foto` char(5)
,`id_jadwal_bs` int(3)
,`hari` enum('Senin','Selasa','Rabu','Kamis','Jumat')
,`jam_mulai` varchar(10)
,`jam_selesai` varchar(10)
,`tanggal_booking` date
,`tanggal_bs` date
,`status_bs` enum('Selesai','Belum Selesai','Batal')
,`id_internal` char(5)
,`nama_internal` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_jadwal`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_jadwal` (
`id_jadwal_prove` int(3)
,`id_internal` char(5)
,`hari` enum('Senin','Selasa','Rabu','Kamis','Jumat')
,`jam_mulai` varchar(10)
,`jam_selesai` varchar(10)
,`status_booking` enum('Free','Unfree')
,`status_aktif` enum('Aktif','Tidak Aktif')
,`terakhir_dibooking` date
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_prove`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_prove` (
`id_prove` char(10)
,`id_eksternal` char(5)
,`nama_eksternal` varchar(50)
,`id_internal` char(5)
,`nama_internal` varchar(50)
,`id_materi_prove` int(3)
,`nama_materi_prove` varchar(30)
,`id_jadwal_prove` int(3)
,`hari` enum('Senin','Selasa','Rabu','Kamis','Jumat')
,`jam_mulai` varchar(10)
,`jam_selesai` varchar(10)
,`deskripsi_materi` tinytext
,`tanggal_booking` date
,`tanggal_prove` date
,`kode_prove` char(10)
,`kata_sandi` char(5)
,`status_prove` enum('Selesai','Belum Selesai','Batal')
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `list_software`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `list_software` (
`kode_bank_s` char(12)
,`id_software` int(11)
,`nama` varchar(50)
);

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
(3, 'Microsoft Excel'),
(4, 'Microsoft Access'),
(5, 'C++'),
(6, 'RStudio'),
(7, 'MATLAB');

-- --------------------------------------------------------

--
-- Struktur dari tabel `prove`
--

CREATE TABLE `prove` (
  `id_prove` char(10) NOT NULL,
  `id_eksternal` char(5) NOT NULL,
  `id_materi_prove` int(3) NOT NULL,
  `id_jadwal_prove` int(3) NOT NULL,
  `deskripsi_materi` tinytext NOT NULL,
  `tanggal_booking` date NOT NULL,
  `tanggal_prove` date NOT NULL,
  `kode_prove` char(10) NOT NULL,
  `kata_sandi` char(5) NOT NULL,
  `status_prove` enum('Selesai','Belum Selesai','Batal') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `prove`
--

INSERT INTO `prove` (`id_prove`, `id_eksternal`, `id_materi_prove`, `id_jadwal_prove`, `deskripsi_materi`, `tanggal_booking`, `tanggal_prove`, `kode_prove`, `kata_sandi`, `status_prove`) VALUES
('200219-001', 'EK001', 6, 7, 'ekekek', '2020-02-19', '2020-02-24', '200219-001', 'k4npQ', 'Belum Selesai');

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
(1, 'Accessx'),
(2, 'MS Office'),
(3, 'Instal Ulang Windows');

-- --------------------------------------------------------

--
-- Struktur untuk view `list_anggota_prove`
--
DROP TABLE IF EXISTS `list_anggota_prove`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_anggota_prove`  AS  select `dp`.`id_detail_prove` AS `id_detail_prove`,`dp`.`id_prove` AS `id_prove`,`dp`.`rating` AS `rating`,`dp`.`deskripsi` AS `deskripsi`,`e`.`id_eksternal` AS `id_eksternal`,`e`.`nama` AS `nama`,`e`.`no_hp` AS `no_hp`,`e`.`akun_line` AS `akun_line`,`e`.`username` AS `username`,`e`.`angkatan` AS `angkatan`,`e`.`foto` AS `foto` from (`detail_prove` `dp` join `eksternal` `e` on((`dp`.`id_eksternal` = `e`.`id_eksternal`))) order by `dp`.`id_detail_prove` limit 60 ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_bank_software`
--
DROP TABLE IF EXISTS `list_bank_software`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_bank_software`  AS  select `bs`.`kode_bank_s` AS `kode_bank_s`,`bs`.`id_eksternal` AS `id_eksternal`,`e`.`nama` AS `nama`,`e`.`no_hp` AS `no_hp`,`e`.`akun_line` AS `akun_line`,`e`.`angkatan` AS `angkatan`,`e`.`foto` AS `foto`,`bs`.`id_jadwal_bs` AS `id_jadwal_bs`,`jbs`.`hari` AS `hari`,`jbs`.`jam_mulai` AS `jam_mulai`,`jbs`.`jam_selesai` AS `jam_selesai`,`bs`.`tanggal_booking` AS `tanggal_booking`,`bs`.`tanggal_bs` AS `tanggal_bs`,`bs`.`status_bs` AS `status_bs`,`jbs`.`id_internal` AS `id_internal`,`i`.`nama` AS `nama_internal` from (((`bank_software` `bs` join `jadwal_bank_software` `jbs` on((`bs`.`id_jadwal_bs` = `jbs`.`id_jadwal_bs`))) join `internal` `i` on((`jbs`.`id_internal` = `i`.`id_internal`))) join `eksternal` `e` on((`bs`.`id_eksternal` = `e`.`id_eksternal`))) order by `bs`.`tanggal_bs` desc,`bs`.`tanggal_booking` desc,`bs`.`kode_bank_s` desc limit 60 ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_jadwal`
--
DROP TABLE IF EXISTS `list_jadwal`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_jadwal`  AS  select `jadwal_prove`.`id_jadwal_prove` AS `id_jadwal_prove`,`jadwal_prove`.`id_internal` AS `id_internal`,`jadwal_prove`.`hari` AS `hari`,`jadwal_prove`.`jam_mulai` AS `jam_mulai`,`jadwal_prove`.`jam_selesai` AS `jam_selesai`,`jadwal_prove`.`status_booking` AS `status_booking`,`jadwal_prove`.`status_aktif` AS `status_aktif`,`jadwal_prove`.`terakhir_dibooking` AS `terakhir_dibooking` from `jadwal_prove` order by `jadwal_prove`.`hari` limit 60 ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_prove`
--
DROP TABLE IF EXISTS `list_prove`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_prove`  AS  select `dp`.`id_prove` AS `id_prove`,`dp`.`id_eksternal` AS `id_eksternal`,`e`.`nama` AS `nama_eksternal`,`i`.`id_internal` AS `id_internal`,`i`.`nama` AS `nama_internal`,`mp`.`id_materi_prove` AS `id_materi_prove`,`mp`.`nama` AS `nama_materi_prove`,`jp`.`id_jadwal_prove` AS `id_jadwal_prove`,`jp`.`hari` AS `hari`,`jp`.`jam_mulai` AS `jam_mulai`,`jp`.`jam_selesai` AS `jam_selesai`,`p`.`deskripsi_materi` AS `deskripsi_materi`,`p`.`tanggal_booking` AS `tanggal_booking`,`p`.`tanggal_prove` AS `tanggal_prove`,`p`.`kode_prove` AS `kode_prove`,`p`.`kata_sandi` AS `kata_sandi`,`p`.`status_prove` AS `status_prove` from (((((`prove` `p` join `eksternal` `e` on((`p`.`id_eksternal` = `e`.`id_eksternal`))) join `jadwal_prove` `jp` on((`p`.`id_jadwal_prove` = `jp`.`id_jadwal_prove`))) join `internal` `i` on((`jp`.`id_internal` = `i`.`id_internal`))) join `materi_prove` `mp` on((`p`.`id_materi_prove` = `mp`.`id_materi_prove`))) join `detail_prove` `dp` on((`p`.`id_prove` = `dp`.`id_prove`))) order by `p`.`id_prove` desc limit 60 ;

-- --------------------------------------------------------

--
-- Struktur untuk view `list_software`
--
DROP TABLE IF EXISTS `list_software`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `list_software`  AS  select `db`.`kode_bank_s` AS `kode_bank_s`,`db`.`id_software` AS `id_software`,`s`.`nama` AS `nama` from ((`detail_bs` `db` join `software` `s` on((`db`.`id_software` = `s`.`id_software`))) join `bank_software` on((`db`.`kode_bank_s` = `bank_software`.`kode_bank_s`))) order by `db`.`id_detail_bs` limit 60 ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `bank_software`
--
ALTER TABLE `bank_software`
  ADD PRIMARY KEY (`kode_bank_s`);

--
-- Indeks untuk tabel `detail_bs`
--
ALTER TABLE `detail_bs`
  ADD PRIMARY KEY (`id_detail_bs`);

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
-- AUTO_INCREMENT untuk tabel `detail_bs`
--
ALTER TABLE `detail_bs`
  MODIFY `id_detail_bs` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT untuk tabel `detail_prove`
--
ALTER TABLE `detail_prove`
  MODIFY `id_detail_prove` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `jadwal_bank_software`
--
ALTER TABLE `jadwal_bank_software`
  MODIFY `id_jadwal_bs` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `jadwal_prove`
--
ALTER TABLE `jadwal_prove`
  MODIFY `id_jadwal_prove` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `materi_prove`
--
ALTER TABLE `materi_prove`
  MODIFY `id_materi_prove` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `software`
--
ALTER TABLE `software`
  MODIFY `id_software` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
