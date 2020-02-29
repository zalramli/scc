<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Absensi extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
        $this->load->model("api/M_absensi");
        date_default_timezone_set('Asia/Jakarta');
    }

    function list_absensi_get()
    {
        // mengambil data dari database
        $query = $this->M_universal->tampil_data('absensi');

        // variable array
        $result = array();
        $result['absensi'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_absensi' => $row["id_absensi"],
                    'id_internal' => $row["id_internal"],
                    'judul_absensi' => $row["judul_absensi"],
                    'tgl_absensi' => $row["tgl_absensi"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_selesai' => $row["jam_selesai"],
                    'status_absensi' => $row["status_absensi"],
                    'kata_sandi' => $row["kata_sandi"],
                );

                array_push($result['absensi'], $data);
            }

            // membuat array untuk di transfer
            $result["success"] = "1";
            $result["message"] = "Berhasil Mengambil Data";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "absensi Masih Kosong";
            $this->response($result, 200);
        }
    }

    function tambah_absensi_post()
    {
        $id_absensi = $this->M_absensi->get_id_absensi();

        $id_internal = $this->post('id_internal');
        $judul_absensi = $this->post('judul_absensi');
        $tgl_absensi = $this->post('tgl_absensi');
        $jam_mulai = $this->post('jam_mulai');
        $jam_selesai = $this->post('jam_selesai');

        $status_absensi = "Belum Selesai";
        $kata_sandi = $this->M_absensi->get_kata_sandi();

        $data = array(
            'id_absensi'   => $id_absensi,
            'id_internal'   => $id_internal,
            'judul_absensi'   => $judul_absensi,
            'tgl_absensi'   => $tgl_absensi,
            'jam_mulai'   => $jam_mulai,
            'jam_selesai'   => $jam_selesai,
            'status_absensi'   => $status_absensi,
            'kata_sandi'   => $kata_sandi,
        );

        $insert =  $this->M_universal->input_data('absensi', $data);
        if ($insert) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Membuat Absensi";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Coba Lagi, Server Error";
            $this->response($result, 200);
        }
    }
}
