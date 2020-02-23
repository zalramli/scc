<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Bank_software extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
        $this->load->model("api/M_bank_software");
        date_default_timezone_set('Asia/Jakarta');
    }

    function tambah_bank_software_post()
    {
        // ambil data
        $kode_bank_s =  $this->M_bank_software->get_kode_bank_s();
        $id_eksternal = $this->post('id_eksternal');
        $id_jadwal_bs = $this->post('id_jadwal_bs');
        $tanggal_booking =  date('Y-m-d');
        $tanggal_bs = $this->post('tanggal_bs');
        $status_bs = "Belum Selesai";

        $data = array(
            'kode_bank_s'   => $kode_bank_s,
            'id_eksternal'   => $id_eksternal,
            'id_jadwal_bs'   => $id_jadwal_bs,
            'tanggal_booking'   => $tanggal_booking,
            'tanggal_bs'   => $tanggal_bs,
            'status_bs'   => $status_bs
        );

        // cek apakah ada list bank_software yang dia sudah buat dan belum selesai ?
        $where_id_eksternal = array(
            'id_eksternal' => $id_eksternal,
            'status_bs' => "Belum Selesai"
        );

        $query = $this->M_universal->get_data('list_bank_software', $where_id_eksternal);
        if ($query->num_rows() > 0) {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["kode_bank_s"] = "null";
            $result["message"] = "Bank Software Anda Sebelumnya Masih Belum Selesai !";
            $this->response($result, 200);
        } else {

            $insert =  $this->M_universal->input_data('bank_software', $data);
            if ($insert) {

                // membuat array untuk di transfer ke API
                $result["success"] = "1";
                $result["kode_bank_s"] = $kode_bank_s;
                $result["message"] = "Berhasil Membuat Bank Software";
                $this->response($result, 200);
            } else {

                // membuat array untuk di transfer ke API
                $result["success"] = "0";
                $result["kode_bank_s"] = "null";
                $result["message"] = "Coba Lagi, Server Error";
                $this->response($result, 200);
            }
        }
    }

    function tambah_detail_bank_software_post()
    {
        // ambil data
        $kode_bank_s = $this->post('kode_bank_s');
        $id_software = $this->post('id_software');

        $data = array(
            'kode_bank_s'   => $kode_bank_s,
            'id_software'   => $id_software
        );

        $insert =  $this->M_universal->input_data('detail_bs', $data);
        if ($insert) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Coba Lagi, Server Error";
            $this->response($result, 200);
        }
    }

    function list_bank_software_post()
    {
        // mengambil data dari database
        $id = $this->post('id');
        $hak_akses = $this->post('hak_akses');

        $query = "";

        if ($hak_akses == "eksternal") {
            $where = array(
                'id_eksternal' => $id
            );
            $query = $this->M_universal->get_data('list_bank_software', $where);
        } else if ($hak_akses == "internal") {
            $query = $this->M_universal->tampil_data('list_bank_software');
        }

        // variable array
        $result = array();
        $result['list_bank_software'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                $tanggal_bs = $row["tanggal_bs"];
                $kode_bank_s = $row["kode_bank_s"];
                $id_jadwal_bs = $row["id_jadwal_bs"];

                $status_bs = $row["status_bs"];

                $data = array();

                if ($tanggal_bs < date('Y-m-d') && $status_bs == "Belum Selesai") {
                    $this->validasi_bank_software_gagal($kode_bank_s, $id_jadwal_bs);
                } else {

                    // ambil detail data db
                    $data = array(
                        'kode_bank_s' => $row["kode_bank_s"],
                        'id_eksternal' => $row["id_eksternal"],
                        'nama' => $row["nama"],
                        'no_hp' => $row["no_hp"],
                        'akun_line' => $row["akun_line"],
                        'angkatan' => $row["angkatan"],
                        'foto' => $row["foto"],
                        'id_jadwal_bs' => $row["id_jadwal_bs"],
                        'hari' => $row["hari"],
                        'jam_mulai' => $row["jam_mulai"],
                        'jam_selesai' => $row["jam_selesai"],
                        'tanggal_booking' => $row["tanggal_booking"],
                        'tanggal_bs' => $row["tanggal_bs"],
                        'status_bs' => $row["status_bs"]
                    );
                    array_push($result['list_bank_software'], $data);
                }
            }

            // membuat array untuk di transfer
            $result["success"] = "1";
            $result["message"] = "Success Mengambil Data";
            $this->response($result, 200);
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Data List Bank Software Tidak Ditemukan";
            $this->response($result, 200);
        }
    }

    function validasi_bank_software_gagal($kode_bank_s, $id_jadwal_bs)
    {
        // untuk mengubah jadwal status bank_software
        $where = array(
            'kode_bank_s' => $kode_bank_s
        );

        $data_update = array(
            'status_bs' => 'Batal'
        );

        $update =  $this->M_universal->update_data($where, "bank_software", $data_update);

        // membuat array untuk di transfer ke API
        $result["success"] = "1";
        $result["message"] = "Berhasil Validasi";
        $this->response($result, 200);
    }

    function list_bank_software_hanya_belum_selesai_post()
    {
        // mengambil data dari database
        $id = $this->post('id');
        $hak_akses = $this->post('hak_akses');

        $query = "";

        if ($hak_akses == "eksternal") {
            $where = array(
                'id_eksternal' => $id,
                'status_bs' => "Belum Selesai"
            );
            $query = $this->M_universal->get_data('list_bank_software', $where);
        } else if ($hak_akses == "internal") {
            $where = array(
                'status_bs' => "Belum Selesai"
            );
            $query = $this->M_universal->get_data('list_bank_software', $where);
        }

        // variable array
        $result = array();
        $result['list_bank_software'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'kode_bank_s' => $row["kode_bank_s"],
                    'id_eksternal' => $row["id_eksternal"],
                    'nama' => $row["nama"],
                    'no_hp' => $row["no_hp"],
                    'akun_line' => $row["akun_line"],
                    'angkatan' => $row["angkatan"],
                    'foto' => $row["foto"],
                    'id_jadwal_bs' => $row["id_jadwal_bs"],
                    'hari' => $row["hari"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_selesai' => $row["jam_selesai"],
                    'tanggal_booking' => $row["tanggal_booking"],
                    'tanggal_bs' => $row["tanggal_bs"],
                    'status_bs' => $row["status_bs"]
                );

                array_push($result['list_bank_software'], $data);
            }

            // membuat array untuk di transfer
            $result["success"] = "1";
            $result["message"] = "Success Mengambil Data";
            $this->response($result, 200);
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Data List Bank Software Tidak Ditemukan";
            $this->response($result, 200);
        }
    }

    function on_hapus_post()
    {
        // ambil data
        $kode_bank_s = $this->post('kode_bank_s');

        $where = array(
            'kode_bank_s'   => $kode_bank_s
        );

        $hapus =  $this->M_universal->hapus_data($where, 'bank_software');

        if ($hapus) {

            // hapus detail
            $hapus =  $this->M_universal->hapus_data($where, 'detail_bs');

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Coba Lagi, Server Error";
            $this->response($result, 200);
        }
    }

    function on_selesai_post()
    {
        // ambil data
        $kode_bank_s = $this->post('kode_bank_s');

        $where = array(
            'kode_bank_s'   => $kode_bank_s
        );

        $data = array(
            'status_bs'   => "Selesai"
        );

        $update =  $this->M_universal->update_data($where, 'bank_software', $data);

        if ($update) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Coba Lagi, Server Error";
            $this->response($result, 200);
        }
    }
}
