<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Jadwal_bs extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
        date_default_timezone_set('Asia/Jakarta');
    }

    function list_jadwal_bs_get()
    {
        // mengambil data dari database
        $query = $this->M_universal->tampil_data('jadwal_bank_software');

        // variable array
        $result = array();
        $result['jadwal_bs'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_jadwal_bs' => $row["id_jadwal_bs"],
                    'id_internal' => $row["id_internal"],
                    'hari' => $row["hari"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_selesai' => $row["jam_selesai"],
                    'status_booking' => $row["status_booking"],
                    'status_aktif' => $row["status_aktif"]
                );

                array_push($result['jadwal_bs'], $data);
            }

            // membuat array untuk di transfer
            $result["success"] = "1";
            $result["message"] = "Berhasil Mengambil Data";
            $this->response($result, 200);
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Jadwal Bank Software Masih Kosong";
            $this->response($result, 200);
        }
    }
}
