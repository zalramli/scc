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
            $result["message"] = "Bank Software Anda Sebelumnya Masih Belum Selesai !";
            $this->response($result, 200);
        } else {

            $insert =  $this->M_universal->input_data('bank_software', $data);
            if ($insert) {

                // membuat array untuk di transfer ke API
                $result["success"] = "1";
                $result["message"] = "Berhasil Membuat Bank Software";
                $this->response($result, 200);
            } else {

                // membuat array untuk di transfer ke API
                $result["success"] = "0";
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
}
