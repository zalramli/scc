<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Internal extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
    }

    function list_internal_post()
    {
        $id_materi_prove = $this->post('id_materi_prove');

        $where_senior = array(
            'status_sj' => "Senior"
        );

        $where_junior = array(
            'status_sj' => "Junior"
        );

        $query = $this->M_universal->tampil_data('internal'); // nilai default tampil semua 

        if ($id_materi_prove == "3" || $id_materi_prove == "4" || $id_materi_prove == "5") {
            // jika access/ axcel / c++
            $query = $this->M_universal->get_data('internal', $where_junior);
        } else if ($id_materi_prove == "6") {
            // jika RStudio
            $query = $this->M_universal->get_data('internal', $where_senior);
        }

        // variable array
        $result = array();
        $result['internal'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_internal' => $row["id_internal"],
                    'nama' => $row["nama"],
                    'no_hp' => $row["no_hp"],
                    'akun_line' => $row["akun_line"],
                    'username' => $row["username"],
                    'hak_akses' => $row["hak_akses"],
                    'jabatan_managerial' => $row["jabatan_managerial"],
                    'status_sj' => $row["status_sj"],
                    'foto' => $row["foto"]
                );

                array_push($result['internal'], $data);
            }

            // membuat array untuk di transfer
            $result["success"] = "1";
            $result["message"] = "Berhasil Mengambil Data";
            $this->response($result, 200);
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Materi Prove Masih Kosong";
            $this->response($result, 200);
        }
    }
}
