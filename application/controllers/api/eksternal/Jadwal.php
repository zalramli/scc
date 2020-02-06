<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Jadwal extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
        date_default_timezone_set('Asia/Jakarta');
    }

    function list_jadwal_post()
    {
        $id_internal = $this->post('id_internal');

        $where = array(
            'id_internal' => $id_internal,
            'status_aktif' => "Aktif"
        );

        // variable array
        $result = array();
        $result['jadwal_prove'] = array();

        // mengambil data dari database
        $query = $this->M_universal->get_data('list_jadwal', $where);

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_jadwal_prove' => $row["id_jadwal_prove"],
                    'id_internal' => $row["id_internal"],
                    'hari' => $row["hari"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_selesai' => $row["jam_selesai"],
                    'status_booking' => $row["status_booking"],
                    'status_aktif' => $row["status_aktif"],
                    'terakhir_dibooking' => $row["terakhir_dibooking"]
                );

                array_push($result['jadwal_prove'], $data);
            }

            // membuat array untuk di transfer
            $result["success"] = "1";
            $result["message"] = "Berhasil Mengambil Data";
            $this->response($result, 200);
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Jadwal Prove Masih Kosong";
            $this->response($result, 200);
        }
    }
}
