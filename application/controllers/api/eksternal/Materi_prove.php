<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Materi_prove extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
    }

    function list_materi_prove_get()
    {
        // mengambil data dari database
        $query = $this->M_universal->tampil_data('materi_prove');

        // variable array
        $result = array();
        $result['materi_prove'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_materi_prove' => $row["id_materi_prove"],
                    'nama' => $row["nama"]
                );

                array_push($result['materi_prove'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "Berhasil Mengambil Data";
                $this->response($result, 200);
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Materi Prove Masih Kosong";
            $this->response($result, 200);
        }
    }

    function ambil_data_materi_prove_post()
    {
        $id_materi_prove = $this->post('id_materi_prove');

        // variable array
        $result = array();
        $result['materi_prove'] = array();

        $data_id = array(
            'id_materi_prove' => $id_materi_prove
        );

        // mengambil data dari database
        $query = $this->M_universal->get_data('materi_prove', $data_id);
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'nama' => $row["nama"],
                );

                array_push($result['materi_prove'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "Berhasil Mengambil Data";
                $this->response($result, 200);
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Data Tidak Ditemukan";
            $this->response($result, 200);
        }
    }
}
