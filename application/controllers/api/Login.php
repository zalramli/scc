<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Login extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_login");
    }

    function index_post()
    {
        // ambil data
        $post_nim = $this->post('nim');
        $post_nama = $this->post('nama');

        $nim = strtolower($post_nim);
        $nama = strtolower($post_nama);

        // data array untuk where db
        $where = array(
            'nim' => $nim
        );

        // mengambil jumlah baris
        $cek = $this->M_login->get_data('peserta', $where)->num_rows();

        // variable array
        $result = array();
        $result['tbl_data'] = array();

        // cek apakah ada data dari nim
        if ($cek > 0) {

            // mengambil data dari database berdasarkan nim
            $query = $this->M_login->get_data('peserta', $where);

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                $db_nama = strtolower($row["nama"]);

                // dicek apakah data inputan sama dengan data di database
                if ($nama == $db_nama) {

                    // ambil detail data db
                    $data = array(
                        'nim' => $row["nim"],
                        'nama' => $row["nama"],
                        'status' => $row["status"],
                        'jabatan' => $row["jabatan"]
                    );

                    array_push($result['tbl_data'], $data);

                    // membuat array untuk di transfer
                    $result["success"] = "2";
                    $result["message"] = "Success Berhasil Masuk";
                    $this->response($result, 200);
                } else {
                    // membuat array untuk di transfer ke API
                    $result["success"] = "1";
                    $result["message"] = "Nama anda tidak terdaftar !";
                    $this->response($result, 200);
                }
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "NRP tidak ditemukan !";
            $this->response($result, 200);
        }
    }
}
