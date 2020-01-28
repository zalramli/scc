<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Akun extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
    }

    function ambil_data_eksternal_post()
    {
        $id_eksternal = $this->post('id_eksternal');

        // variable array
        $result = array();
        $result['eksternal'] = array();

        $data_id = array(
            'id_eksternal' => $id_eksternal
        );

        // mengambil data dari database
        $query = $this->M_universal->get_data('eksternal', $data_id);
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'nama' => $row["nama"],
                    'no_hp' => $row["no_hp"],
                    'akun_line' => $row["akun_line"],
                    'username' => $row["username"],
                    'angkatan' => $row["angkatan"],
                    'foto' => $row["foto"]
                );

                array_push($result['eksternal'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "Berhasil Mengambil Data";
                $this->response($result, 200);
            }
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Data Tidak ditemukan Di dalam Server";
            $this->response($result, 200);
        }
    }
}
