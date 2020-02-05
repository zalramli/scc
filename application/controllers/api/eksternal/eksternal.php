<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Eksternal extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
    }

    function list_eksternal_post()
    {
        $id_prove = $this->post('id_prove');

        $where = array(
            'id_prove' => $id_prove,
        );

        $query = $this->M_universal->get_data('list_anggota_prove', $where);

        // variable array
        $result = array();
        $result['eksternal'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_eksternal' => $row["id_eksternal"],
                    'nama' => $row["nama"],
                    'no_hp' => $row["no_hp"],
                    'akun_line' => $row["akun_line"],
                    'username' => $row["username"],
                    'angkatan' => $row["angkatan"],
                    'foto' => $row["foto"]
                );

                array_push($result['eksternal'], $data);
            }

            // membuat array untuk di transfer
            $result["success"] = "1";
            $result["message"] = "Berhasil Mengambil Data";
            $this->response($result, 200);
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Anggota Eksternal Masih Kosong";
            $this->response($result, 200);
        }
    }
}
