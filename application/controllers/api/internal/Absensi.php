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
                    'id_internal' => $row["id_internal"]
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
}
