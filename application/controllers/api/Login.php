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
        $post_username = $this->post('username');
        $post_password = $this->post('password');

        $username = strtolower($post_username);
        $password = strtolower($post_password);

        // variable array
        $result = array();
        $result['tbl_data'] = array();

        // data array untuk where db
        $where = array(
            'username' => $username
        );

        // mengambil jumlah baris
        $cek = $this->M_login->get_data('internal', $where)->num_rows();

        // cek apakah ada data dari username
        if ($cek > 0) {

            // mengambil data dari database berdasarkan username
            $query = $this->M_login->get_data('internal', $where);

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                $db_password = strtolower($row["password"]);

                // dicek apakah data inputan dengan data di database
                if ($password == $db_password) {

                    // ambil detail data db
                    $data = array(
                        'id_user' => $row["id_internal"],
                        'nama' => $row["nama"],
                        'username' => $row["username"],
                        'hak_akses' => "internal"
                    );

                    array_push($result['tbl_data'], $data);

                    // membuat array untuk di transfer
                    $result["success"] = "2";
                    $result["message"] = "Success Berhasil Masuk";
                    $this->response($result, 200);
                } else {
                    // membuat array untuk di transfer ke API
                    $result["success"] = "1";
                    $result["message"] = "Password anda salah !";
                    $this->response($result, 200);
                }
            }
        } else {

            // mengambil jumlah baris
            $cek = $this->M_login->get_data('eksternal', $where)->num_rows();

            // cek apakah ada data dari username
            if ($cek > 0) {

                // mengambil data dari database berdasarkan username
                $query = $this->M_login->get_data('eksternal', $where);

                // mengeluarkan data dari database
                foreach ($query->result_array() as $row) {

                    $db_password = strtolower($row["password"]);

                    // dicek apakah data inputan dengan data di database
                    if ($password == $db_password) {

                        // ambil detail data db
                        $data = array(
                            'id_user' => $row["id_eksternal"],
                            'nama' => $row["nama"],
                            'username' => $row["username"],
                            'hak_akses' => "eksternal"
                        );

                        array_push($result['tbl_data'], $data);

                        // membuat array untuk di transfer
                        $result["success"] = "2";
                        $result["message"] = "Success Berhasil Masuk";
                        $this->response($result, 200);
                    } else {
                        // membuat array untuk di transfer ke API
                        $result["success"] = "1";
                        $result["message"] = "Password anda salah !";
                        $this->response($result, 200);
                    }
                }
            } else {

                // membuat array untuk di transfer ke API
                $result["success"] = "1";
                $result["message"] = "Username anda tidak ditemukan !";
                $this->response($result, 200);
            }
        }
    }
}
